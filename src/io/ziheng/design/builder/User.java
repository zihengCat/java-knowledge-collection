package io.ziheng.design.builder;

public class User {

    private final String name;
    private final int age;
    private final String phone;
    private final String address;

    public User(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.age = userBuilder.age;
        this.phone = userBuilder.phone;
        this.address = userBuilder.address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static UserBuilder getUserBuilder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private int age;
        private String phone;
        private String address;
        public UserBuilder() {
            // ...
        }
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }
        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }
        // Return the finally consrcuted User object
        public User build() {
            User user = new User(this);
            validateUserObject(user);
            return user;
        }
        private void validateUserObject(User user) {
            // Do some basic validations to check 
            // if user object does not break any assumption of system
            if (user.name == null || user.name.length() > 256) {
                throw new IllegalArgumentException();
            }
        }
    }
}
/* EOF */