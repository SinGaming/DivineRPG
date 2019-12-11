package divinerpg.utils;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GoalHelper {
    public static void removeAll(GoalSelector selector, Class<? extends Goal>... classes) {
        Set<PrioritizedGoal> goals = ReflectionHelper.getFieldValue(selector, "goals", Set.class);

        if (goals == null || goals.isEmpty())
            return;

        // Get all classes of removed goals
        List<Class<? extends Goal>> allClasses = Arrays.asList(classes);
        // find all goals to delete
        List<PrioritizedGoal> toRemove = goals.stream().filter(x -> allClasses.contains(x.getGoal().getClass())).collect(Collectors.toList());

        goals.removeAll(toRemove);
    }
}
