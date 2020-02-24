package divinerpg.entities.base.render;

import com.mojang.blaze3d.matrix.MatrixStack;

public class DeobfHelper {

    /**
     * Calls push -> pop in try statement
     *
     * @param stack
     * @return
     */
    public static void useMatirx(MatrixStack stack, Runnable runnable) {
        stack.func_227860_a_();
        runnable.run();
        stack.func_227865_b_();
    }

    /**
     * Scales matirx
     *
     * @param stack
     * @param x
     */
    public static void scale(MatrixStack stack, float x) {
        scale(stack, x, x, x);
    }

    /**
     * Scales matirx
     */
    public static void scale(MatrixStack stack, float x, float y, float z) {
        stack.func_227862_a_(x, y, z);
    }

    /**
     * Translate matrix
     */
    public static void translate(MatrixStack stack, double x, double y, double z) {
        stack.func_227861_a_(x, y, z);
    }
}
