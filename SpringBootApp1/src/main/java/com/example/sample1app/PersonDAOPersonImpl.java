package com.example.sample1app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOPersonImpl implements PersonDAO<Person>{
    private static final long SerialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

//    public PersonDAOPersonImpl{
//        super();
//    }

    @Override
    public List<Person> getAll(){
        // Criteria APIを使った書き方
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        query.select(root);
        @SuppressWarnings("unchecked")
        List<Person> list = (List<Person>)entityManager.createQuery(query).getResultList();

        // クエリを使った書き方
        // Query query = entityManager.createQuery("from Person");
        // List<Person> list = query.getResultList();
        // entityManager.close();
        return list;
    }

     @Override
     public Person findById(final long id){
        return (Person)entityManager.createQuery("from Person where id = " + id).getSingleResult();
     }

    @Override
    public List<Person> findByName(final String name){
        // TODO: SQLインジェクションに対応
        return (List<Person>)entityManager.createQuery("from Person where name = '" + name + "'").getResultList();
    }

    @Override
    public List <Person> find(String fstr){
        // (TODO) 初期値にnull代入する必要ある? query.getResultListが空リスト返すなら不要な気が...
        List<Person> list = null;
        Long fid = 0L;
        try{
            fid = Long.parseLong(fstr);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }

        // Criteria APIを使った書き方
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        query.select(root)
                .where(builder
                        .or(
                                builder.equal(root.get("id"), fid),
                                builder.like(root.get("name"), "%" + fstr + "%"),
                                builder.like(root.get("mail"), fstr + "%@%")));

        list = (List<Person>)entityManager.createQuery(query).getResultList();

        // NamedQueryを使った書き方
        // Query query = entityManager.createNamedQuery("findWithName")
        //        .setParameter("fname", "%" + fstr + "%");

        // クエリを直接記述する書き方
//        String qstr = "from Person where id = ?1 or name like ?2 or mail like ?3";
//        Query query = entityManager.createQuery(qstr)
//                .setParameter(1, fid)
//                .setParameter(2, "%" + fstr + "%")
//                .setParameter(3, fstr + "%@%");
        // list = query.getResultList();

        return list;
    }

    @Override
    public List<Person> findByAge(int min, int max){
        List<Person> list = null;
        Query query = entityManager.createNamedQuery("findByAge")
                .setParameter("min", min)
                .setParameter("max", max);
        list = query.getResultList();
        return list;
    }
}
