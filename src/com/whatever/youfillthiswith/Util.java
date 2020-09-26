package com.whatever.youfillthiswith;

import org.bukkit.entity.Player;

public class Util {
	@Deprecated
	public static int getTotalExperience(Player Who) {
        int level = Who.getLevel();
        float progress = Who.getExp();
        int totalExp = 0;
        for (int n = 1; n < level + 1; n++) totalExp = (n >= 16) ? ((n >= 31) ? totalExp + 112 + (n - 31) * 9 : totalExp + 37 + (n - 16) * 5) : totalExp + 7 + (n - 1) * 2;
        progress = (level >= 15 ) ? ((level >= 30) ? progress * (112 + (level - 30) * 9) : progress * (37 + (level - 15) * 5)) : progress * (7 + level * 2);
        totalExp = totalExp + Math.round(progress);
        return totalExp;
    }
}
