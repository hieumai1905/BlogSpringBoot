package com.example.webblog.servies.type;

import com.example.webblog.models.Post;
import com.example.webblog.models.Type;
import com.example.webblog.repositories.PostRepository;
import com.example.webblog.repositories.TypeRepository;
import com.example.webblog.servies.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService implements ITypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private IPostService postService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            List<Post> posts = (List<Post>) postRepository.getAllByTypeTypeId(id);
            for (Post post : posts) {
                postService.remove(post.getPostId());
            }
            typeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findTypeByTypeName(name);
    }
}