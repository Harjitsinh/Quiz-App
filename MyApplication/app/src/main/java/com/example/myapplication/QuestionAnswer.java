package com.example.myapplication;

public class QuestionAnswer {

    public static String[][] getQuestionsForTopic(String topic) {
        switch (topic) {
            case "C":
                return new String[][] {
                        {"What is C?", "A language", "A tool", "A database", "None", "A language"},
                        {"C is mainly used for?", "Web Dev", "OS Dev", "Data Entry", "Gaming", "OS Dev"},
                        {"C is a?", "Scripting Lang", "Markup Lang", "Programming Lang", "None", "Programming Lang"},
                        {"C was created by?", "Dennis Ritchie", "James Gosling", "Guido Rossum", "Bjarne Stroustrup", "Dennis Ritchie"},
                        {"C follows?", "OOP", "Procedural", "Functional", "None", "Procedural"}
                };
            case "Java":
                return new String[][] {
                        {"Java is a?", "Compiler", "Interpreter", "Both", "None", "Both"},
                        {"JVM stands for?", "Java Virtual Machine", "Java Visual Model", "Just Virtual Model", "None", "Java Virtual Machine"},
                        {"Java was developed by?", "Google", "Microsoft", "Sun Microsystems", "Oracle", "Sun Microsystems"},
                        {"Java supports?", "Multi-threading", "Single-threading", "Only Parallel", "None", "Multi-threading"},
                        {"Java is mainly used for?", "Android Dev", "OS Dev", "Networking", "Data Entry", "Android Dev"}
                };
            case "Python":
                return new String[][] {
                        {"Python was created by?", "Guido Rossum", "James Gosling", "Linus Torvalds", "Bjarne Stroustrup", "Guido Rossum"},
                        {"Python follows?", "OOP", "Procedural", "Both", "None", "Both"},
                        {"Python is a?", "Compiler", "Interpreter", "Both", "None", "Interpreter"},
                        {"Python is used in?", "AI", "Web Dev", "Data Science", "All", "All"},
                        {"Which symbol is used for comments?", "#", "//", "/* */", "--", "#"}
                };
            default:
                return new String[][] {};
        }
    }
}
