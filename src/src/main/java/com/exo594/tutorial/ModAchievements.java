package com.exo594.tutorial;

import static com.exo594.tutorial.block.ModBlocks.tutorial_ore;
import com.exo594.tutorial.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements {

    public static Achievement tutorialItem;
    public static Achievement tutoriumRound;
    public static Achievement expensiveFuckery;

    public static AchievementPage TutorialPage;

    public static final void init() {

        tutorialItem = new Achievement("achievement.tutorialItem", "tutorialItem", 0, 0,
                Item.getItemFromBlock(tutorial_ore), (Achievement) null)
                .initIndependentStat().registerStat();

        tutoriumRound = new Achievement("achievement.tutoriumRound", "tutoriumRound",
                0, 3, ModItems.craftingPendulum, tutorialItem).setSpecial().registerStat();
        
        expensiveFuckery = new Achievement("achievement.expensiveFuckery", "expensiveFuckery",
                3, 0, Items.diamond, tutorialItem).registerStat();

        TutorialPage = new AchievementPage("\u00a7aTut Achievements", tutorialItem, tutoriumRound, expensiveFuckery);
        
        AchievementPage.registerAchievementPage(TutorialPage);
    }

}
