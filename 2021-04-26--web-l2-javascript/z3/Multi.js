const PLAYER1 = 'fa-circle-o';
const PLAYER2 = 'fa-times';
let round = 1;
var board = [
    ['', '', ''],
    ['', '', ''],
    ['', '', '']
];

function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}

// const combinations = [
//     [0, 1, 2], [3, 4, 5], [6, 7, 8],
//     [0, 3, 6], [1, 4, 7], [2, 5, 8],
//     [0, 4, 8], [2, 4, 6]
// ];


var combinations;
if (confirm("Wygrana po przekątnej?")) {
    combinations = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],
        [0, 3, 6], [1, 4, 7], [2, 5, 8],
        [0, 4, 8], [2, 4, 6]
    ];
} else {
    combinations = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8]
        [0, 3, 6], [1, 4, 7], [2, 5, 8],
        // [0, 4, 8], [2, 4, 6]
    ];
}

var with_computer = false;
if (confirm("Gra z komputerem?")) {
    with_computer = true;
}

var win = false;

const boxes = [...document.querySelectorAll('.kwadrat')];
boxes.forEach(kwadrat => kwadrat.addEventListener('click', klikniecie));

document.getElementById('player').innerHTML = "O";

function klikniecie(event) {
    if (!win) {
        if (document.getElementById('player').innerHTML == "O")
            document.getElementById('player').innerHTML = "X";
        else
            document.getElementById('player').innerHTML = "O";

        if (with_computer)
            document.getElementById('player').innerHTML = "O";
    }
    else {
        document.getElementById('player').innerHTML = "żaden - koniec gry.";
    }



    if (with_computer == false) {
        const { row, column } = event.target.dataset;
        const turn = round % 2 === 0 ? PLAYER2 : PLAYER1;
        if (board[row][column] !== '') return;
        event.target.classList.add(turn);
        board[row][column] = turn;
        round++;
        console.log(check());
    } else {

        const { row, column } = event.target.dataset;
        var turn = PLAYER1;
        if (board[row][column] !== '') return;
        event.target.classList.add(turn);
        board[row][column] = PLAYER1;
        round++;
        // console.log(check());

        var turn = PLAYER2;
        // event.target.classList.add(turn);
        // board[0][0] = PLAYER2;
        // document.getElementById('00').classList.add(turn);
        for (; ;) {
            var x = getRandomInt(3);
            var y = getRandomInt(3);

            if (board[x][y] === '') {
                board[x][y] = PLAYER2;
                document.getElementById(x.toString() + y.toString()).classList.add(turn);
                round++;
                console.log(check());
                break;
            }
        }
    }



}

function check() {
    const result = board.reduce((total, row) => total.concat(row));
    let winner = null;
    let moves = {
        'fa-times': [],
        'fa-circle-o': []
    };
    result.forEach((field, index) => moves[field] ? moves[field].push(index) : null);
    console.log(moves);
    combinations.forEach(combination => {
        if (combination.every(index => moves[PLAYER1].indexOf(index) > -1)) {
            //winner = 'Winner: Player 1';
            alert("Wygrał gracz O");
            win = true;
        }
        if (combination.every(index => moves[PLAYER2].indexOf(index) > -1)) {
            //winner = 'Winner: Player 2';
            alert("Wygrał gracz X");
            win = true;
        }
    });

    return winner;
}