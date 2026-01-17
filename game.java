/* --- All-in-One Banking Game Logic --- */

// 1. Database of 10 mixed questions (Basics, Security, Math, Needs/Wants)
const questions = [
    { q: "What is the main purpose of a bank?", a: ["To store toys", "To keep money safe", "To play games"], c: 1 },
    { q: "What is 'Interest'?", a: ["A reward for saving money", "A fine for being late", "Tax on snacks"], c: 0 },
    { q: "Which account is best for students to save money?", a: ["Current Account", "Savings Account", "Business Account"], c: 1 },
    { q: "A 'Minor Account' is for kids under what age?", a: ["10 years old", "21 years old", "18 years old"], c: 2 },
    { q: "If your balance is ₹1500 and you deposit ₹500, what is the new total?", a: ["₹1000", "₹2000", "₹1500"], c: 1 },
    { q: "If you have ₹1000 and withdraw ₹300, how much is left?", a: ["₹700", "₹1300", "₹300"], c: 0 },
    { q: "What should you do while entering your PIN at an ATM?", a: ["Say it out loud", "Cover the keypad", "Show it to a stranger"], c: 1 },
    { q: "If someone calls asking for your OTP, you should:", a: ["Tell them immediately", "Hang up and ignore", "Ask them why they need it"], c: 1 },
    { q: "Is 'Life-saving Medicine' a Need or a Want?", a: ["Need", "Want"], c: 0 },
    { q: "Is a 'New Video Game' a Need or a Want?", a: ["Need", "Want"], c: 1 }
];

let currentStep = 0;
let score = 0;

// 2. Start Game Function
function startGame() {
    document.getElementById('start-screen').classList.add('hide');
    document.getElementById('game-screen').classList.remove('hide');
    loadStep();
}

// 3. Load the Current Question
function loadStep() {
    const step = questions[currentStep];
    
    // Update Text
    document.getElementById('step-info').innerText = `Question ${currentStep + 1} of 10`;
    document.getElementById('question-text').innerText = step.q;
    
    // Update Progress Bar
    const progressPercent = ((currentStep) / 10) * 100;
    document.getElementById('progress-fill').style.width = progressPercent + "%";

    // Clear and create new option buttons
    const box = document.getElementById('options-box');
    box.innerHTML = '';

    step.a.forEach((option, index) => {
        const btn = document.createElement('button');
        btn.innerText = option;
        btn.classList.add('btn');
        btn.onclick = () => checkAnswer(btn, index);
        box.appendChild(btn);
    });
}

// 4. Validate Answer and Provide Feedback
function checkAnswer(selectedBtn, userChoice) {
    const correctIdx = questions[currentStep].c;
    const allBtns = document.querySelectorAll('#options-box .btn');

    // Disable buttons so the user cannot click twice
    allBtns.forEach(b => b.disabled = true);

    if (userChoice === correctIdx) {
        selectedBtn.classList.add('correct');
        score++;
    } else {
        selectedBtn.classList.add('wrong');
        // Visually show the correct answer to help the student learn
        allBtns[correctIdx].classList.add('correct');
    }

    // Wait 1.5 seconds so the student can see the feedback
    setTimeout(() => {
        currentStep++;
        if (currentStep < questions.length) {
            loadStep();
        } else {
            showResults();
        }
    }, 1500);
}

// 5. Final Score Screen
function showResults() {
    document.getElementById('game-screen').classList.add('hide');
    document.getElementById('result-screen').classList.remove('hide');
    
    // Set the final mark
    document.getElementById('final-score').innerText = `${score} / 10`;

    // Personalized Feedback
    let msg = "";
    if(score === 10) {
        msg = "Perfect! You are a Certified Money Master! 🌟";
    } else if(score >= 7) {
        msg = "Great job! You have strong banking skills! ✅";
    } else {
        msg = "Good effort! Read the booklet again to stay safe. 📚";
    }
    
    document.getElementById('feedback-msg').innerText = msg;
}
