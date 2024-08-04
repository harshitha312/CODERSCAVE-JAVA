import java.util.*;
abstract class Question {
    String questionText;
    Question(String questionText) {
        this.questionText = questionText;
    }
    abstract boolean checkAnswer(String answer);
}
class MultipleChoiceQuestion extends Question {
    private Map<String, Boolean> options;
    MultipleChoiceQuestion(String questionText, Map<String, Boolean> options) {
        super(questionText);
        this.options = options;
    }
    @Override
    boolean checkAnswer(String answer) {
        return options.getOrDefault(answer, false);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(questionText);
        options.forEach((key, value) -> sb.append("\n").append(key));
        return sb.toString();
    }
}
class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    TrueFalseQuestion(String questionText, boolean correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }
    @Override
    boolean checkAnswer(String answer) {
        return (correctAnswer && "True".equalsIgnoreCase(answer)) ||
               (!correctAnswer && "False".equalsIgnoreCase(answer));
    }
    @Override
    public String toString() {
        return questionText + "\n(True/False)";
    }
}
class ShortAnswerQuestion extends Question {
    private String correctAnswer;
    ShortAnswerQuestion(String questionText, String correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }
    @Override
    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
    @Override
    public String toString() {
        return questionText;
    }
}
class Quiz {
    private String title;
    private List<Question> questions;
    Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }
    void addQuestion(Question question) {
        questions.add(question);
    }
    void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        for (Question question : questions) {
            System.out.println(question);
            String userAnswer = scanner.nextLine();
            if (question.checkAnswer(userAnswer)) {
                score++;
            }
        }
        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }
}
public class OnlineQuizPlatform {
    public static void main(String[] args) {
        Quiz quiz = new Quiz("General Knowledge Quiz");
        Map<String, Boolean> options1 = new HashMap<>();
        options1.put("A. 5", false);
        options1.put("B. 7", true);
        options1.put("C. 8", false);
        options1.put("D. 10", false);
        quiz.addQuestion(new MultipleChoiceQuestion("What is 2 + 5?", options1));
        quiz.addQuestion(new TrueFalseQuestion("The Earth is flat.", false));
        quiz.addQuestion(new ShortAnswerQuestion("What is the capital of France?", "Paris"));
        quiz.takeQuiz();
    }
}