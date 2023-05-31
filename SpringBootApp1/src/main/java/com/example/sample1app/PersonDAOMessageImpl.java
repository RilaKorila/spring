package com.example.sample1app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOMessageImpl implements PersonDAO<Message>{
    private static final long SerialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    public PersonDAOMessageImpl(){
        super();
    }

    @Override
    public List<Message> getAll(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root);
        List<Message> list = (List<Message>) entityManager.createQuery(query).getResultList();

        return list;
    }

    @Override
    public Message findById(final long id){
        return (Message)entityManager.createQuery("from Message where id = " + id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findByName(final String name){
        // TODO: SQLインジェクションに対応
        return (List<Message>)entityManager.createQuery("from Message where name = '" + name + "'").getResultList();
    }

    @Override
    public List <Message> find(String fstr){
        // (TODO) 初期値にnull代入する必要ある? query.getResultListが空リスト返すなら不要な気が...
        List<Message> list = null;

        // Criteria APIを使った書き方
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root)
                .where(builder.equal(root.get("content"), fstr));

        list = (List<Message>)entityManager.createQuery(query).getResultList();
        return list;
    }

    @Override // 使わない
    public List<Message> findByAge(int min, int max){
        return null;
    }

    @Override
    public List<Message> getPage(int page, int limit){
        // 取り出す位置
        int offset = page * limit;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root);

        return (List<Message>) entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
