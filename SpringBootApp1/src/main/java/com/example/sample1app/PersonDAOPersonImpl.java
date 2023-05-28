package com.example.sample1app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
        Query query = entityManager.createQuery("from Person");

        // @SuppressWarnings("unchecked");
        List<Person> list = query.getResultList();
        entityManager.close();
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
        String qstr = "from Person where id = :fstr or name like :fname or mail like :fmail";
        Long fid = 0L;
        try{
            fid = Long.parseLong(fstr);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
        Query query = entityManager.createQuery(qstr)
                .setParameter("fstr", fid)
                .setParameter("fname", fstr)
                .setParameter("fmail", fstr);
        list = query.getResultList();

        return list;
    }
}
