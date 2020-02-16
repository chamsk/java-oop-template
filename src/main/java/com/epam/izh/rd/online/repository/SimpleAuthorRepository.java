package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if(author==null){
            return false;
        }
        if(findByFullName(author.getName(),author.getLastName())!=null){
            return false;
        }
        Author[] temp = new Author[authors.length + 1];
        for (int i=0;i<authors.length;i++) {
            temp[i] = authors[i];
        }
        temp[authors.length] = author;
        authors = temp;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author:authors) {
            if (author.getName() == name && author.getLastName() == lastname){
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if(author==null){
            return false;
        }
        if(findByFullName(author.getName(),author.getLastName())==null){
            return false;
        }
        Author[] temp = new Author[authors.length-1];
        int tempVar = 0;
        for (int i=0;i<authors.length;i++) {
            if(!(authors[i].equals(author))){
                temp[tempVar] = authors[i];
                tempVar++;
            }
        }
        authors = temp;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
