import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuizApplication {

    // Question Class
    static class Question {
        private String questionText;
        private String[] options;
        private int correctAnswer;

        public Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }
    }

    // Quiz Class
    static class Quiz {
        private ArrayList<Question> questions;
        private int score;
        private int currentQuestionIndex;

        public Quiz() {
            questions = new ArrayList<>();
            score = 0;
            currentQuestionIndex = 0;
        }

        public void addQuestion(Question question) {
            questions.add(question);
        }

        public void startQuiz() {
            Scanner scanner = new Scanner(System.in);
            Collections.shuffle(questions); // Shuffle questions for randomness
            System.out.println("Welcome to the Quiz!\n");

            for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
                Question currentQuestion = questions.get(currentQuestionIndex);
                System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestionText());
                
                String[] options = currentQuestion.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }

                System.out.print("Enter your answer (1-4): ");
                int userAnswer = scanner.nextInt();
                checkAnswer(userAnswer - 1); // Adjusting for 0-based index
            }

            System.out.println("\nQuiz Over! Your Score: " + score + "/" + questions.size());
            scanner.close();
        }

        private void checkAnswer(int userAnswer) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (userAnswer == currentQuestion.getCorrectAnswer()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong! Correct answer: " + 
                    (currentQuestion.getCorrectAnswer() + 1) + ". " + 
                    currentQuestion.getOptions()[currentQuestion.getCorrectAnswer()] + "\n");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Sample Questions
        quiz.addQuestion(new Question("What is the capital of France?", 
            new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        quiz.addQuestion(new Question("Which programming language is platform-independent?", 
            new String[]{"C", "C++", "Java", "Python"}, 2));
        quiz.addQuestion(new Question("What is 5 + 3?", 
            new String[]{"5", "8", "10", "15"}, 1));
        quiz.addQuestion(new Question("Which planet is known as the Red Planet?", 
            new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        quiz.addQuestion(new Question("What is the largest planet in our solar system?", 
            new String[]{"Mars", "Jupiter", "Saturn", "Neptune"}, 1));
        quiz.addQuestion(new Question("What is the smallest planet in our solar system?", 
            new String[] {"Mercury", "Venus", "Earth", "Mars"}, 3));    
        quiz.addQuestion(new Question("What is the largest mammal on Earth?", 
            new String[]{"Elephant", "Whale", "Lion", "Giraffe"}, 1));
        quiz.addQuestion(new Question("What is the most widely spoken language?", 
            new String[]{"English", "Spanish", "Mandarin", "Arabic"}, 3));
        quiz.addQuestion(new Question("What is the highest mountain peak?", 
            new String[]{"Everest", "K2", "Kilimanjaro", "Mount Fuji"}, 0));
        quiz.addQuestion(new Question("What is the boiling point of water?", 
            new String[]{"100", "50", "0", "212"}, 0));
        quiz.addQuestion(new Question("What is the world's largest waterfall?", 
            new String[]{"Victoria Falls", "Iguazu Falls", "Angel Falls", "Niagara Falls"}, 0));
        quiz.addQuestion(new Question("What is the chemical symbol for gold?", 
            new String[]{"Ag", "Au", "Hg", "Pb"}, 1));
        quiz.addQuestion(new Question("What is the number of wheels on a unicycle?", 
            new String[]{"1", "2", "3", "4"}, 0));
        quiz.addQuestion(new Question("What is the most popular sport in the world?", 
            new String[]{"Soccer", "Basketball", "Football", "Cricket"}, 0));
        quiz.addQuestion(new Question("What is the largest living species of lizard?", 
            new String[]{"Komodo dragon", "Saltwater crocodile", "Black mamba", "Green anaconda"}, 0));
        quiz.addQuestion(new Question("What is the highest recorded temperature on Earth?", 
            new String[]{"134", "122", "120", "110"}, 0));
         
        // Start Quiz
        quiz.startQuiz();
    }
}
