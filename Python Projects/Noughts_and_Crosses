import random
import time
import math

class Player:
    def __init__(self, letter):
        # define what letter the player is (x or o)
        self.letter = letter

    def get_move(self, game):
        pass


class ComputerPlayer(Player):  # takes parents properties!
    def __init__(self, letter):
        super().__init__(letter)

    def get_move(self, game):
        choice = random.choice(game.available_positions())
        return choice
    
class UnbeatableComputerPlayer(Player):
    def __init__(self, letter):
        super().__init__(letter)

    def get_move(self, game):
        if len(game.available_positions()) == 9:
            choice = random.choice(game.available_positions())
        else:
            choice = self.minimax(game, self.letter)
        return choice
    
    def minimax(self, state, player):
        max_player = self.letter
        if (player == 'O'):
            other_player = 'X'
        else:
            other_player = 'O'

        if state.current_winner == other_player: # dictionary of position and score!
            return {'position': None,
                    'score': 1 * (state.empty_space_amount() + 1) if other_player else -1 * (state.empty_space_amount() + 1)}
        elif not state.empty_space():
            return {'position': None, 'score': 0}
        
        if player == max_player:
            best = {'position': None, 'score': -math.inf} # score maximised
        else:
            best = {'position': None, 'score': math.inf} # score minimised
        for possible_move in state.available_positions():
            state.make_choice(possible_move, player)
            simulated_score = self.minimax(state, other_player) # alternate players

            state.board[possible_move] = ' ' # revert movement
            state.current_winner = None
            print(simulated_score)
            simulated_score['position'] = possible_move

            if player == max_player:
                if simulated_score['score'] > best['score']: # maximise
                    best = simulated_score
            else: # not max player
                if simulated_score['score'] < best['score']: #minimise
                    best = simulated_score


class HumanPlayer(Player): # it's a you!
    def __init__(self, letter):
        super().__init__(letter)

    def get_move(self, game):
        valid_choice = False # user needs to make a decision
        value = None # current user input
        while not valid_choice:
            choice = input(f'it\'s {self.letter} \'s turn. Please input a move (board positions 0-8):')
            try:
                value = int(choice) # try to convert users input to a integer
                if value not in game.available_positions():
                    raise ValueError # trigger the except condition
                return value
            except ValueError:
                print('Invalid choice, please try again (an available choice between 0-8)')


class NoughtsandCrosses:
    def __init__(self):
        self.board = [' ' for _ in range(9)] # 3x3 Board
        self.current_winner = None

    def display_board(self):
        for row in [self.board[i*3:(i+1)*3] for i in range(3)]: # board layout
            print('| ' + ' | '.join(row) + ' |')

    @staticmethod
    def display_board_numbers():
        # board index row1: 0,1,2 -> row2: 3,4,5 -> row3: 6,7,8
        number_board = [[str(i) for i in range(j*3, (j+1)*3)] for j in range(3)]
        for row in number_board:
            print('| ' + ' | '.join(row) + ' |')

    def make_choice(self, position, letter):
        if (self.board[position] == ' '): # empty space
            self.board[position] = letter
            if self.winner(position, letter):
                self.current_winner = letter
            return True
        return False
        
    def winner(self, position, letter): # 3 in a row = winner!
        #row check
        row_index = position // 3
        row = self.board[row_index*3 : (row_index + 1) * 3]
        if all([space == letter for space in row]):
            return True # 3 in a row from a row
        
        # column check
        column_index = position % 3
        column = [self.board[column_index+i*3] for i in range(3)]
        if all([space == letter for space in column]):
            return True # 3 in a row from a column
        
        # diagonal check
        if position % 2 == 0: # if even
            first_diagonal = [self.board[i] for i in [0, 4, 8]] # left to right!
            if all([space == letter for space in first_diagonal]):
                return True # 3 in a row left to right
            second_diagonal = [self.board[i] for i in [2, 4, 6]] # right to left!
            if all([space == letter for space in second_diagonal]):
                return True # 3 in a row right to left
        
        



    def available_positions(self):
        moves = []
        return [i for i, space in enumerate(self.board) if space == ' ']
        
    def empty_space(self):
        return ' ' in self.board # True if empty
    
    def empty_space_amount(self):
        return len(self.available_positions())

def play(game, nought, cross, display_game=True): # play against the computer player!!! game=False for computer vs computer
    if display_game:
        game.display_board_numbers()

    letter = 'O' # Noughts start!

    while game.empty_space():
        if letter == 'X': # Player = Cross
            turn = cross.get_move(game)
        else: # player = Nought
            turn = nought.get_move(game)

        if game.make_choice(turn, letter): # make a decision
            if display_game:
                print(f'{letter} makes a move to square {turn}')
                game.display_board()
                print('')

            if game.current_winner:
                if display_game:
                    print(f'{letter} is the winner!!!')
                return letter


            if letter == 'X': # switch to opposite player after they have made a move!
                letter = ' O'
            else: 
                letter = 'X'

        time.sleep(1.5)

    if display_game:
        print('It\'s a tie :(')

if __name__ == '__main__':
    nought = HumanPlayer('O')
    cross = ComputerPlayer('X')
    #cross = UnbeatableComputerPlayer('X')
    nandc = NoughtsandCrosses()
    play(nandc, nought, cross, display_game=True)