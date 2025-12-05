package edu.io.token;

import edu.io.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SluiceboxTest {

    @Test
    void sluicebox_default_gainFactor_is_1_2() {
        var sluicebox = new SluiceboxToken();
        Assertions.assertEquals(1.2, sluicebox.gainFactor(), 0.001);
    }

    @Test
    void sluicebox_default_durability_is_5() {
        var sluicebox = new SluiceboxToken();
        Assertions.assertEquals(5, sluicebox.durability());
    }

    @Test
    void sluicebox_gain_decreases_with_use() {
        var sluicebox = new SluiceboxToken();
        double initialGain = sluicebox.gainFactor();

        sluicebox.useWith(new GoldToken()).ifWorking(() -> {});

        Assertions.assertEquals(initialGain - 0.04, sluicebox.gainFactor(), 0.001);
    }

    @Test
    void sluicebox_gain_cannot_go_below_1_0() {
        var sluicebox = new SluiceboxToken(1.05, 10);

        for (int i = 0; i < 10; i++) {
            sluicebox.useWith(new GoldToken()).ifWorking(() -> {});
        }

        Assertions.assertEquals(1.0, sluicebox.gainFactor(), 0.001);
    }

    @Test
    void sluicebox_works_with_gold() {
        var player = new Player();
        player.interactWithToken(new SluiceboxToken());
        player.interactWithToken(new GoldToken(2.0));

        double expected = 2.0 * 1.2;
        Assertions.assertEquals(expected, player.gold.amount(), 0.001);
    }

    @Test
    void sluicebox_not_repairable() {
        var sluicebox = new SluiceboxToken();
        Assertions.assertFalse(sluicebox instanceof Repairable);
    }

    @Test
    void sluicebox_use_decreases_durability() {
        var sluicebox = new SluiceboxToken();
        int initialDurability = sluicebox.durability();

        sluicebox.useWith(new GoldToken()).ifWorking(() -> {});

        Assertions.assertEquals(initialDurability - 1, sluicebox.durability());
    }

    @Test
    void broken_sluicebox_is_unusable() {
        var player = new Player();
        var sluicebox = new SluiceboxToken(1.2, 1);
        player.interactWithToken(sluicebox);

        player.interactWithToken(new GoldToken(2.0));

        Assertions.assertEquals(0, sluicebox.durability());
        Assertions.assertTrue(sluicebox.isBroken());
    }

    @Test
    void throws_when_gainFactor_not_greater_than_1() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SluiceboxToken(1.0, 5));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SluiceboxToken(0.5, 5));
    }

    @Test
    void throws_when_durability_not_positive() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SluiceboxToken(1.2, 0));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SluiceboxToken(1.2, -1));
    }
}