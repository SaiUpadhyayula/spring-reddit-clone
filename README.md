# spring-reddit-clone
Reddit clone built using Spring Boot, Spring Security with JPA Authentication, Spring Data JPA with MySQL, Spring MVC. The frontend is built using Angular - You can find the frontend source code here - https://github.com/SaiUpadhyayula/angular-reddit-clone

# Recent Changes

- Updated to latest spring boot version - 2.6.2
- Added 'spring-boot-starter-validation' dependency in pom.xml file to support Java Bean Validation annotations.
- *IMPORTANT*: Removed Legacy JWT Authentication Mechanism and replaced it with latest Spring Security JWT Support.
- Adapted SecurityConfig.java class according to latest Spring Security configuration settings, see method configure(AuthenticationManagerBuilder) inside SecurityConfig.java class
- Set spring.jpa.open-in-view property as false.


# NOTE
This source code is used as a starting point for my other tutorials which shows how to deploy the Spring Boot and Angular app to Heroku and Testing Spring boot applications:

The complete source code for the ![Deploy Spring Boot and Angular to Heroku](https://www.youtube.com/watch?v=y_idn12FB18&t=8s) can be found at https://github.com/SaiUpadhyayula/spring-reddit-clone-heroku-deployment

The complete source code for the ![Testing Spring Boot Applications](https://youtu.be/--nQfs67zCM) can be found at https://github.com/SaiUpadhyayula/spring-boot-testing-reddit-clone

# Tutorial
https://programmingtechie.com/2019/09/30/build-a-full-stack-reddit-clone-with-spring-boot-and-angular-part-1/

# Front end code
https://github.com/SaiUpadhyayula/angular-reddit-clone

# Screenshots
1. Home Page

![Home Page](https://github.com/SaiUpadhyayula/spring-reddit-clone/blob/master/src/main/resources/images/reddit-screenshot-updated.PNG)

2. View Post Page

![View Post Page](https://github.com/SaiUpadhyayula/spring-reddit-clone/blob/master/src/main/resources/images/reddit-screenshot-updated.PNG)

3. Create Post Page

![Create Post Page](https://github.com/SaiUpadhyayula/spring-reddit-clone/blob/master/src/main/resources/images/create-post.PNG)

4. Create Subreddit Page

![Create Subreddit Page](https://github.com/SaiUpadhyayula/spring-reddit-clone/blob/master/src/main/resources/images/create-subreddit.PNG)

5. User Profile Page

![User Profile Page](https://github.com/SaiUpadhyayula/spring-reddit-clone/blob/master/src/main/resources/images/user-profile.PNG)
