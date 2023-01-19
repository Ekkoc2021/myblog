package com.ekko.myblog.controller.adminController;

import com.ekko.myblog.pojo.*;
import com.ekko.myblog.service.*;
import com.ekko.myblog.util.Converter;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    UserService userService;
    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogAndConvert(id);
        userService.setBlogUser(blog);
        blog.setTags(tagService.getTagsByBlogId(blog.getId()));
        model.addAttribute("blog",blog);
        return "admin/blog";
    }

    @GetMapping({"/blogs"})
    public String myblogs(BlogQuery blogQuery, Model model, HttpSession session){
        blogQuery.reSet();
        blogQuery.setRecommend(true);
        Long id = ((User) session.getAttribute("user")).getId();
        blogQuery.setUserid(id);//设置查询的userid
        if (blogQuery.getPage()<=0){
            blogQuery.setPage(1);
        }
        //查询用户的所有结果:下一页反正走的也是search,所以调用相同的方法
        PageInfo<Blog> page2 = blogService.getBlogs(blogQuery);
        model.addAttribute("page", page2);
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String blogSearch(BlogQuery blogQuery , Model model, HttpSession session){
        blogQuery.reSet();
        blogQuery.setUserid(((User)session.getAttribute("user")).getId());
        if(blogQuery.getPage()<=0){
            blogQuery.setPage(1);
        }
        PageInfo<Blog> page2 = blogService.getBlogs(blogQuery);
        model.addAttribute("page", page2);
        return "admin/blogs::blogList";
    }
    @Autowired
    private TagService tagService;
    @GetMapping("/blogs/input")
    public String blogInput(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags", tagService.listTags());
        return "admin/blogs-input";
    }
    @GetMapping("/blogs/{id}/input")
    @Transactional
    public String editBlog(Model model,@PathVariable Long id,RedirectAttributes redirectAttributes){
        //需要查询
        Blog blog = blogService.getBlog(id);
        if (blog!=null) {
            List<Tag> tags=tagService.getTagsByBlogId(blog.getId());
            blog.setTags(tags);
            String ids=Converter.tagsToIds(blog.getTags());
            blog.setTagIds(ids);
            model.addAttribute("blog", blog);
            model.addAttribute("types", typeService.listType());
            model.addAttribute("tags", tagService.listTags());
            return "admin/blogs-input";
        }
        redirectAttributes.addFlashAttribute("message","博客不存在!");
        return "redirect:/admin/blogs";//重定向到blogs页面
    }

    @Autowired
    BlogTagService blogTagService;
    @PostMapping("/blogs")
    @Transactional //这里是必须要用事务的,涉及了两个表的添加使用
    public String addBlog(Blog blog, RedirectAttributes redirectAttributes, HttpSession session){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if(blog.getId()==null) {
            //由于添加和修改共用一个界面,同时使用一个表单提交的url
            //完成添加操作
            //初始化数据
            blog.setCreateTime(timestamp);
            blog.setUpdateTime(timestamp);
            blog.setId(System.currentTimeMillis());
            User user = (User) session.getAttribute("user");
            blog.setViews(0);
            blog.setUserId(user.getId());
            if (blog.getTypename() == null || "".equals(blog.getTypename())) {
                blog.setTypename("无类型");
            }
            if (blog.getFlag() == null || "".equals(blog.getFlag())) {
                blog.setFlag("原创");
            }
            blog.setViews(0);
            blogService.addBlogs(blog);
            if (!"".equals(blog.getTagIds()) && blog.getTagIds() != null) {
                List<Long> TagsId = Converter.TagsIdCon(blog.getTagIds());
                List<BlogTag> blogTags = new ArrayList<>();
                for (Long id : TagsId) {
                    blogTags.add(new BlogTag(blog.getId(), id));
                }
                blogTagService.addBlogTags(blogTags);
            }
            redirectAttributes.addFlashAttribute("message", "添加成功!");
        }else{
            //完成更新操作:1,更新博客信息-blog,2,更新博客标签中间表信息-blog_tag
            blog.setUpdateTime(timestamp);
            blogService.editBlog(blog);
            //2,更新blog_tag,考虑到这个两都是多对多的关系.从而我们采用先删除再添加的操作
            blogTagService.deleteBlogTag(blog.getId());
            //添加操作
            if (!"".equals(blog.getTagIds()) && blog.getTagIds() != null) {
                List<Long> TagsId = Converter.TagsIdCon(blog.getTagIds());
                List<BlogTag> blogTags = new ArrayList<>();
                for (Long id : TagsId) {
                    blogTags.add(new BlogTag(blog.getId(), id));
                }
                blogTagService.addBlogTags(blogTags);
            }
            redirectAttributes.addFlashAttribute("message", "更新成功!");
        }
        return "redirect:/admin/blogs";//重定向到blogs页面
    }

    @GetMapping("/blogs/{id}/delete")
    @Transactional
    public String deleteBlog(@PathVariable("id")Long id,RedirectAttributes redirectAttributes){
        //删除博客
        blogService.deleteBlog(id);
        //删除博客相关标签
        blogTagService.deleteBlogTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功!");
        return "redirect:/admin/blogs";//重定向到blogs页面
    }

}
