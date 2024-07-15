#  City Service

<br>

## Overview
  In this project, we will see how to <strong>integrate the city API</strong> to store their information in <strong>Redis</strong> and benefit from it.
  
 <br>
 
## Usages
- Spring WebFlux
- WebClient
- Redisson "Redis"  
- Lombok
    
<br> 

## Architecture of the Project

 ### 1-src folder
   - Client
   - Configration
   - Controller
   - Model
   - Service
   
### 2-Maven pom.xml
<br> 
    
```
<dependencies>
		<dependency>
			<groupId>org.redisson</groupId>
			<artifactId>redisson</artifactId>
			<version>3.20.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
 ```

<br>

###### Output :star_struck:

##### :pencil2: `As you can see first photo, the time of fetch city information by zip code take some whole UNLIKE second photo take 7 millie second when it come from redis.` 
<img width="1333" alt="Screenshot 1446-01-09 at 11 10 44 AM" src="https://github.com/user-attachments/assets/fb4a9646-9962-4c2a-a435-373d8a0f7c47">

<img width="1333" alt="Screenshot 1446-01-09 at 11 11 46 AM" src="https://github.com/user-attachments/assets/af924b38-2e2d-4cdd-882e-765279da1305">

---


### Good Luck <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="30px"> 






