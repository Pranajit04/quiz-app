<?php

$conn = mysqli_connect("localhost",
         "root","","my_quiz_db","3306");

$stmt = $conn->prepare("SELECT `question`,
               `option1`,`option2`,`option3`,
               `option4`,`correct_option`
               FROM
               `maths_table`");

// execute the query
$stmt->execute();

//Binding the results to the query
//when the prepared statement is executed,the values from the selected columns
//will be stored in these variables
$stmt->bind_result($question,$option1,$option2,$option3,$option4,$correct_option);

//creating an  empty array
$question_array=array();

//Travelling through all the questions
while($stmt->fetch()){
    $temp = array();
    
    $temp["question"] = $question;
    $temp["option1"] = $option1;
    $temp["option2"] = $option2;
    $temp["option3"] = $option3;
    $temp["option4"] = $option4;
    $temp["correct_option"] = $correct_option;
    
    array_push($question_array, $temp);
}

echo json_encode($question_array);
?>