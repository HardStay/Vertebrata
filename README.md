<<<<<<< HEAD
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## launch dari mainLogin.java kemudian jika error ikuti panduan dibawah.

## di folder .vscode lalu file launch.json tambahkan code berikut.
,"vmArgs": " -Dprism.maxvram=500m -Dprism.order=j2d -Djavafx.animation.fullspeed=true -Dprism.poolstats=true -Xmx8G -ea"

contohnya 

{

	"type": "java",
 
      "name": "MainLogin",
      
      "request": "launch",
      
      "mainClass": "login.MainLogin",
      
      "projectName": "wildshield_a6359276",
      
}

jadi 

{

      "type": "java",
      
      "name": "MainLogin",
      
      "request": "launch",
      
      "mainClass": "login.MainLogin",
      
      "projectName": "wildshield_a6359276",
      
      "vmArgs": " -Dprism.maxvram=500m -Dprism.order=j2d -Djavafx.animation.fullspeed=true -Dprism.poolstats=true -Xmx8G -ea"
      
}

## Link Demo

https://www.canva.com/design/DAFoM5T3nuA/PhPb1xixJKXtCQXB5_UbpQ/edit?utm_content=DAFoM5T3nuA&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
=======
# Vertebrata
>>>>>>> 0c624a5379cc65909e9d4450a332f48a24de58fa
