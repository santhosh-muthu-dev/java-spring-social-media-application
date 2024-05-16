package com.rest.webservices.restfulwebservices.post;

import com.rest.webservices.restfulwebservices.JPA.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDAOService {

    @Autowired
    private final PostRepository postRepository;

    public PostDAOService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
