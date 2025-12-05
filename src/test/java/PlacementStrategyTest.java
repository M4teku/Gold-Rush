import edu.io.Board;
import edu.io.strategy.RandomPlacementStrategy;
import edu.io.strategy.SequentialPlacementStrategy;
import edu.io.token.GoldToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlacementStrategyTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void can_set_placement_strategy() {
        RandomPlacementStrategy strategy = new RandomPlacementStrategy();

        board.setPlacementStrategy(strategy);

        assertNotNull(board.getPlacementStrategy());
        assertTrue(board.getPlacementStrategy() instanceof RandomPlacementStrategy);
    }

    @Test
    void sequential_strategy_finds_first_available_square() {
        SequentialPlacementStrategy strategy = new SequentialPlacementStrategy();

        Board.Coords coords = strategy.findAvailableSquare(board);

        assertNotNull(coords);
        assertEquals(0, coords.col());
        assertEquals(0, coords.row());
    }

    @Test
    void random_strategy_finds_available_square() {
        RandomPlacementStrategy strategy = new RandomPlacementStrategy();

        Board.Coords coords = strategy.findAvailableSquare(board);

        assertNotNull(coords);
        assertTrue(coords.col() >= 0 && coords.col() < board.size());
        assertTrue(coords.row() >= 0 && coords.row() < board.size());
    }

    @Test
    void throws_when_board_full() {
        // Wypełnia całą planszę
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                board.placeToken(i, j, new GoldToken());
            }
        }

        assertThrows(IllegalStateException.class, () -> {
            board.getAvailableSquare();
        });
    }

    @Test
    void default_strategy_is_sequential() {
        assertTrue(board.getPlacementStrategy() instanceof SequentialPlacementStrategy);
    }
}