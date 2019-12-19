package divinerpg.entities.bosses.ayeraco.model;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

/**
 * Helping class to store friendly ayeracos together
 *
 * @param <T>
 */
public class EntityFinder<T extends Entity> {
    private final World world;
    private final UUID id;
    private Class<? extends Entity> clazz;
    private T finded;

    /**
     * Can search entity from UUID by class
     *
     * @param clazz
     * @param world
     * @param id
     */
    public EntityFinder(Class<? extends Entity> clazz, World world, UUID id) {
        this.clazz = clazz;
        this.world = world;
        this.id = id;
    }

    /**
     * Already find enriry
     *
     * @param entity
     */
    public EntityFinder(T entity) {
        this(entity.getClass(), entity.world, entity.getUniqueID());
    }

    /**
     * Performs search
     *
     * @return
     */
    public T find() {
        if (finded == null) {
            finded = world instanceof ClientWorld
                    ? find(((ClientWorld) world))
                    : find(((ServerWorld) world));
        }

        return finded;
    }

    /**
     * Search on client
     *
     * @param world - client world
     * @return
     */
    private T find(ClientWorld world) {
        for (Entity entity : world.getAllEntities()) {
            if (entity.getUniqueID() == id) {

                if (entity.getClass() == clazz) {
                    return (T) entity;
                }

                break;
            }
        }

        return null;
    }

    /**
     * Search on server
     *
     * @param world - server world
     * @return
     */
    private T find(ServerWorld world) {
        Entity entity = world.getEntityByUuid(id);

        return entity != null && entity.getClass() == clazz
                ? (T) entity
                : null;
    }
}
