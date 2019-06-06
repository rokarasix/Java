package programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


class Student{
    int id;
    String name;
    long marks;
    Student(int id,String name,long marks){
        this.id=id;
        this.name=name;
        this.marks=marks;
    }
}
public class ComparingStudents {


    public static void main(String[] args) {
        ComparingStudents obj=new ComparingStudents();
      obj.disp();

    }

    public void disp() {

        Student stu = new Student(1, "ram", 85l);
        Student stu2 = new Student(2, "rahim", 89l);
        Student stu3 = new Student(3, "sita", 89l);

        List<Student> list = new ArrayList<Student>();
        list.add(stu);
        list.add(stu2);
        list.add(stu3);



        Comparator<Student>  comparingStudent = new Comparator<Student>() {

            public int compare(Student o1, Student o2) {

                if (o1.marks == o2.marks) {
                    if(o1.id==o2.id) {
                        return o1.name.compareTo(o2.name);
                    }
                    else{
                      return String.valueOf(o1.id).compareTo(String.valueOf(o2.id));
                    }
                }
                else {
                    return String.valueOf(o2.marks).compareTo(String.valueOf(o1.marks));
                }
            }
        };

        Collections.sort(list, comparingStudent);

        Iterator<Student> itr=list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next().name);
        }


    }


}
