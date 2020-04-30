package com.example.formativeone;

import androidx.annotation.NonNull;

public class Student {

        private String id;
        private String name;
        private String password;
        private String  email;
        String  gender;

        public String getPassword(){
            return this.password;
        }
        public void setPassword(String pass){
             this.password = pass;
        }

        public String getEmail(){
            return this.email;
        }

        public void setEmail(String email){
             this.email = email;
        }

        public Student() {
        }

        public Student(String id, String name, String password, String email) {
            this.id = id;
            this.name = name;
            this.password = password;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @NonNull
        @Override
        public String toString() {
            return this.getId()+" "+  this.getName() + " "+this.getEmail();
        }
}
