package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        if(book==null){
            return false;
        }
        SchoolBook[] temp = new SchoolBook[schoolBooks.length + 1];
        for (int i=0;i<schoolBooks.length;i++) {
            temp[i] = schoolBooks[i];
        }
        temp[schoolBooks.length] = book;
        schoolBooks = temp;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] result = new SchoolBook[0];
        for (int i=0;i<schoolBooks.length;i++) {
            if(schoolBooks[i].getName()==name){
                int size = result.length;
                SchoolBook[] temp = new SchoolBook[size+1];
                  for(int j=0;j<size;j++){
                       temp[j]=result[j];
                  }
                temp[size] = schoolBooks[i];
                result=temp;
            }
        }
        return result;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] foundArray = findByName(name);
        int sizeFoundArray = foundArray.length;
        int index = 0;
        SchoolBook[] temp = new SchoolBook[schoolBooks.length-sizeFoundArray];
        if(name==null){
            return false;
        }
        if(sizeFoundArray==0){
            return false;
        }
        for(int i=0;i<schoolBooks.length;i++){
            SchoolBook tempBook = schoolBooks[i];
            for(int j=0;j<sizeFoundArray;j++){
                if(schoolBooks[i].equals(foundArray[j])){
                    tempBook = null;
                }
            }
            if(tempBook!=null){
                temp[index] = tempBook;
                index++;
            }
        }
        schoolBooks = temp;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
