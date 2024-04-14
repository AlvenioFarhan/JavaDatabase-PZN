package programmer.zaman.now.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmer.zaman.now.database.entity.Comment;
import programmer.zaman.now.database.repository.CommentRepository;
import programmer.zaman.now.database.repository.CommentRepositoryImpl;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("alvenio@gmail.com", "Hello");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println("Berhasil menambah data");
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(2004);
        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment notFound = commentRepository.findById(1000000);
        Assertions.assertNotNull(notFound);
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        System.out.println(comments.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> comments = commentRepository.findAllByEmail("alvenio@gmail.com");
        System.out.println(comments.size());
    }
}
