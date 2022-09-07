package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.Chel;

import java.util.List;

public interface ChelRepository extends CrudRepository<Chel, Long> {
    public List<Chel> findByName(String name);
    public List<Chel> findByNickname(String nickname);
    public List<Chel> findByNameContains(String name);
    public List<Chel> findByNicknameContains(String nickname);
}
