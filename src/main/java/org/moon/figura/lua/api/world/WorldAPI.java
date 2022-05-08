package org.moon.figura.lua.api.world;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.moon.figura.lua.LuaWhitelist;
import org.moon.figura.lua.docs.LuaFunctionOverload;
import org.moon.figura.lua.docs.LuaMethodDoc;
import org.moon.figura.lua.docs.LuaTypeDoc;
import org.moon.figura.math.vector.FiguraVec3;
import org.moon.figura.utils.LuaUtils;
import org.terasology.jnlua.LuaRuntimeException;

@LuaWhitelist
@LuaTypeDoc(
        name = "WorldAPI",
        description = "world"
)
public class WorldAPI {

    public static final WorldAPI INSTANCE = new WorldAPI();

    public static Level getCurrentWorld() {
        return Minecraft.getInstance().level;
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = BiomeWrapper.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = BiomeWrapper.class
                    )
            },
            description = "world.get_biome"
    )
    public static BiomeWrapper getBiome(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("getBiome", x, y, z);
        BiomeWrapper result = new BiomeWrapper(getCurrentWorld().getBiome(pos.asBlockPos()).value());
        pos.free();
        return result;
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = BlockStateWrapper.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = BlockStateWrapper.class
                    )
            },
            description = "world.get_blockstate"
    )
    public static BlockStateWrapper getBlockState(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("getBlockState", x, y, z);
        BlockPos blockPos = pos.asBlockPos();
        pos.free();
        Level world = getCurrentWorld();
        if (world.getChunkAt(blockPos) == null)
            return null;
        return new BlockStateWrapper(world.getBlockState(blockPos));
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = Integer.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = Integer.class
                    )
            },
            description = "world.get_restone_power"
    )
    public static Integer getRedstonePower(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("getRedstonePower", x, y, z);
        BlockPos blockPos = pos.asBlockPos();
        pos.free();
        if (getCurrentWorld().getChunkAt(blockPos) == null)
            return null;
        return getCurrentWorld().getBestNeighborSignal(blockPos);
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = Integer.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = Integer.class
                    )
            },
            description = "world.get_strong_redstone_power"
    )
    public static Integer getStrongRedstonePower(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("getStrongRedstonePower", x, y, z);
        BlockPos blockPos = pos.asBlockPos();
        pos.free();
        if (getCurrentWorld().getChunkAt(blockPos) == null)
            return null;
        return getCurrentWorld().getDirectSignalTo(blockPos);
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = {},
                            argumentNames = {},
                            returnType = Double.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = Double.class,
                            argumentNames = "delta",
                            returnType = Double.class
                    )
            },
            description = "world.get_time"
    )
    public static double getTime(Double delta) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        if (delta == null) delta = 0d;
        return getCurrentWorld().getGameTime() + delta;
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = {},
                            argumentNames = {},
                            returnType = Double.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = Double.class,
                            argumentNames = "delta",
                            returnType = Double.class
                    )
            },
            description = "world.get_time_of_day"
    )
    public static double getTimeOfDay(Double delta) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        if (delta == null) delta = 0d;
        return getCurrentWorld().getDayTime() + delta;
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = @LuaFunctionOverload(
                    argumentTypes = {},
                    argumentNames = {},
                    returnType = Integer.class
            ),
            description = "world.get_moon_phase"
    )
    public static int getMoonPhase() {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        return getCurrentWorld().getMoonPhase();
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = {},
                            argumentNames = {},
                            returnType = Double.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = Double.class,
                            argumentNames = "delta",
                            returnType = Double.class
                    )
            },
            description = "world.get_rain_gradient"
    )
    public static double getRainGradient(Float delta) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        if (delta == null) delta = 1f;
        return getCurrentWorld().getRainLevel(delta);
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = @LuaFunctionOverload(
                    argumentTypes = {},
                    argumentNames = {},
                    returnType = Boolean.class
            ),
            description = "world.is_thundering"
    )
    public static boolean isThundering() {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        return getCurrentWorld().isThundering();
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = Integer.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = Integer.class
                    )
            },
            description = "world.get_light_level"
    )
    public static Integer getLightLevel(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("getLightLevel", x, y, z);
        BlockPos blockPos = pos.asBlockPos();
        pos.free();
        Level world = getCurrentWorld();
        if (world.getChunkAt(blockPos) == null)
            return null;
        world.updateSkyBrightness();
        return world.getLightEngine().getRawBrightness(blockPos, world.getSkyDarken());
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = Integer.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = Integer.class
                    )
            },
            description = "world.get_sky_light_level"
    )
    public static Integer getSkyLightLevel(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("getSkyLightLevel", x, y, z);
        BlockPos blockPos = pos.asBlockPos();
        pos.free();
        Level world = getCurrentWorld();
        if (world.getChunkAt(blockPos) == null)
            return null;
        return world.getBrightness(LightLayer.SKY, blockPos);
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = Integer.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = Integer.class
                    )
            },
            description = "world.get_block_light_level"
    )
    public static Integer getBlockLightLevel(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("getBlockLightLevel", x, y, z);
        BlockPos blockPos = pos.asBlockPos();
        pos.free();
        Level world = getCurrentWorld();
        if (world.getChunkAt(blockPos) == null)
            return null;
        return world.getBrightness(LightLayer.BLOCK, blockPos);
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaFunctionOverload(
                            argumentTypes = FiguraVec3.class,
                            argumentNames = "pos",
                            returnType = Boolean.class
                    ),
                    @LuaFunctionOverload(
                            argumentTypes = {Double.class, Double.class, Double.class},
                            argumentNames = {"x", "y", "z"},
                            returnType = Boolean.class
                    )
            },
            description = "world.is_open_sky"
    )
    public static Boolean isOpenSky(Object x, Double y, Double z) {
        if (!exists()) throw new LuaRuntimeException("World does not exist!");
        FiguraVec3 pos = LuaUtils.parseVec3("isOpenSky", x, y, z);
        BlockPos blockPos = pos.asBlockPos();
        pos.free();
        Level world = getCurrentWorld();
        if (world.getChunkAt(blockPos) == null)
            return null;
        return world.canSeeSky(blockPos);
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = @LuaFunctionOverload(
                    argumentTypes = {},
                    argumentNames = {},
                    returnType = Boolean.class
            ),
            description = "world.exists"
    )
    public static boolean exists() {
        return getCurrentWorld() != null;
    }

}