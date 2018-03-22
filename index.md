Project Description - What is the high level idea of your project? This should be similar to your daily log submission (but could have changed). 2. Project Design - lower-level description. a. What technologies will you use, how will they integrate? b. What are the project’s use cases? c. Any other design docs that are necessary for me to understand your project 3. Project Schedule - what will you have done at each checkpoint? What will the final project look like? 4. Project Justification - why is your project interesting? a. Novelty - why is your project different than existing applications, and why should people use it? b. Complexity - why is this project a good showcase of your abilities?

1.	My idea that I have would be a finance type game. 
It will simulate as an application that will act as a fake bank, with each user starting out with a set balance.
The user can spend money on things such as bills and items, take out loans from a bank if need be, and can sell items to other users for money or have a "job" that will give them a steady income.
It will also show a credit score based on the user’s spending habits, which is the key part of the whole program.
This will essentially act as a preview to life after college in terms of controlling finances.
The way that this will work is this: The user will login (the password is encoded), then, if it is their first time logging in, they will select a job, home, and car. Depending on how lucrative/expensive the job or home/car is, that will determine the users income and bills due. They will be paid from their job on a set time frame, and will have to pay bills in a simlilar time frame. If the user does not have the funds to pay, they can take out a loan from the bank, but their credit score will suffer. If they do not pay their bills in a set amount of time that it has been given, not only will the user accumulate debt, but they could also lose their car/home, and lose the game. 

2.	Lower-level description

a.	It will implement MySQL in order to save the bank and user data, such as items, jobs, balances, etc. 
It will also involve password hashing so that it is secure to each user, and will prevent any online attacks (such as SQL injections, ARP poisioning, DDoS attacks, etc.).
It will also use SpringBoot, as well as many HTML pages using thymeleaf. I will have to change the main database from H2 to MySQL.

b.	Use cases include: 
receiving money from a job, sending money to users/the bank for bills, 
checking account balance, 
checking a set credit score based on spending habits and watching it increment/decrement.

c.	The credit scores will be made with a base of 650 and will go up and down depending on the amount that you receive vs. the amount you spend

3.	I should have the home page with empty links up 2 weeks after spring break. 
For the next checkpoint (Checkpoint 1), I will have a registration/login use case made. 
For Checkpoint 2, I will have the web application have users get a home/car, as well as a job, but not all the financial aspects will be set yet. 
For Checkpoint 3, I will have the credit score ready and will tweak the application so that it looks nicer, as well as hfix the other financial problems (if there are any).
For the final project, I will make sure that everything works seamlessly as well as make the application look aesthetically pleasing. 
The final project will have a modern-feel UI where users can do simple bank actions in an aesthetically pleasing way.

4.	Project justification

a.	This application will be a good gateway into post-college life. As college students, we normally don't have to worry about certain bills, such as rent, utilties, car insurance, medical insurance, etc. This will be a good preview of how to prioritize your finances so that you don't end up in the red. People should use it to not only give/receive money, but to see how that influences their credit score.

b.	I believe that this will show everything that I have learned in this class from simple network programming, to Spring MVC, to using MySQL. 
This will also test my security abilities with hashing the passwords as well. 
I believe that this will be an application that I can proudly talk about in job interviews in the future because it not only tests my skills, but it can help students in the future to not be in financial trouble.
