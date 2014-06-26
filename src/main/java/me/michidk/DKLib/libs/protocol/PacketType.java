package me.michidk.DKLib.libs.protocol;

import me.michidk.DKLib.libs.protocol.event.PacketListener;

import java.util.ArrayList;

/** 
 * @author Marco
 * @version 1.1.0
 */

public abstract interface PacketType {

	public abstract String getPacketClassName();

	public abstract Class<?> getPacketClass();
	
	public abstract ClassAccessor getClassAccessor();

	public abstract int getLegacyId();

	public abstract ArrayList<PacketListener> getPacketListener();

	public enum Client implements PacketType {

		KEEP_ALIVE("PacketPlayInKeepAlive", 0),
		CHAT("PacketPlayInChat", 3),
		USE_ENTITY("PacketPlayInUseEntity", 7),
		FLYING("PacketPlayInFlying", 10),
		POSITION("PacketPlayInPosition", 11),
		LOOK("PacketPlayInLook", 12),
		POSITION_LOOK("PacketPlayInPositionLook", 13),
		BLOCK_DIG("PacketPlayInBlockDig", 14),
		BLOCK_PLACE("PacketPlayInBlockPlace", 15),
		HELD_ITEM_SLOT("PacketPlayInHeldItemSlot", 16),
		ARM_ANIMATION("PacketPlayInArmAnimation", 18),
		ENTITY_ACTION("PacketPlayInEntityAction", 19),
		STEER_VEHICLE("PacketPlayInSteerVehicle", 27),
		CLOSE_WINDOW("PacketPlayInCloseWindow", 101),
		WINDOW_CLICK("PacketPlayInWindowClick", 102),
		TRANSACTION("PacketPlayInTransaction", 106),
		SET_CREATIVE_SLOT("PacketPlayInSetCreativeSlot", 107),
		ENCHANT_ITEM("PacketPlayInEnchantItem", 108),
		UPDATE_SIGN("PacketPlayInUpdateSign", 130),
		ABILITIES("PacketPlayInAbilities", 202),
		TAB_COMPLETE("PacketPlayInTabComplete", 203),
		SETTINGS("PacketPlayInSettings", 204),
		CLIENT_COMMAND("PacketPlayInClientCommand", 205),
		CUSTOM_PAYLOAD("PacketPlayInCustomPayload", 250);

		private String className;
		private Class<?> packetClass;
		private ClassAccessor accessor;
		private int legacy;
		private ArrayList<PacketListener> listener;

		Client(String className, int legacy) {
			this.className = className;
			this.legacy = legacy;
			this.packetClass = ReflectionUtil.getMinecraftClass(className);
			if(this.packetClass != null){
				this.accessor = new ClassAccessor(this.packetClass);
			}
			this.listener = new ArrayList<>();
		}

		@Override
		public String getPacketClassName() {
			return className;
		}

		@Override
		public Class<?> getPacketClass() {
			return packetClass;
		}
		
		@Override
		public ClassAccessor getClassAccessor(){
			return accessor;
		}

		@Override
		public int getLegacyId() {
			return legacy;
		}

		@Override
		public ArrayList<PacketListener> getPacketListener() {
			return listener;
		}
	}

	public enum Server implements PacketType {

		KEEP_ALIVE("PacketPlayOutKeepAlive", 0),
		LOGIN("PacketPlayOutLogin", 1),
		CHAT("PacketPlayOutChat", 3),
		UPDATE_TIME("PacketPlayOutUpdateTime", 4),
		ENTITY_EQUIPMENT("PacketPlayOutEntityEquipment", 5),
		SPAWN_POSITION("PacketPlayOutSpawnPosition", 6),
		UPDATE_HEALTH("PacketPlayOutUpdateHealth", 8),
		RESPAWN("PacketPlayOutRespawn", 9),
		POSITION("PacketPlayOutPosition", 13),
		HELD_ITEM_SLOT("PacketPlayOutHeldItemSlot", 16),
		BED("PacketPlayOutBed", 17),
		ANIMATION("PacketPlayOutAnimation", 18),
		NAMED_ENTITY_SPAWN("PacketPlayOutNamedEntitySpawn", 20),
		COLLECT("PacketPlayOutCollect", 22),
		SPAWN_ENTITY("PacketPlayOutSpawnEntity", 23),
		SPAWN_ENTITY_LIVING("PacketPlayOutSpawnEntityLiving", 24),
		SPAWN_ENTITY_PAINTING("PacketPlayOutSpawnEntityPainting", 25),
		SPAWN_ENTITY_EXPERIENCE_ORB("PacketPlayOutSpawnEntityExperienceOrb", 26),
		ENTITY_VELOCITY("PacketPlayOutEntityVelocity", 28),
		ENTITY_DESTROY("PacketPlayOutEntityDestroy", 29),
		ENTITY("PacketPlayOutEntity", 30),
		REL_ENTITY_MOVE("PacketPlayOutRelEntityMove", 31),
		ENTITY_LOOK("PacketPlayOutEntityLook", 32),
		ENTITY_TELEPORT("PacketPlayOutEntityTeleport", 34),
		ENTITY_HEAD_ROTATION("PacketPlayOutEntityHeadRotation", 35),
		ENTITY_STATUS("PacketPlayOutEntityStatus", 38),
		ATTACH_ENTITY("PacketPlayOutAttachEntity", 39),
		ENTITY_METADATA("PacketPlayOutEntityMetadata", 40),
		ENTITY_EFFECT("PacketPlayOutEntityEffect", 41),
		REMOVE_ENTITY_EFFECT("PacketPlayOutRemoveEntityEffect", 42),
		EXPERIENCE("PacketPlayOutExperience", 43),
		UPDATE_ATTRIBUTES("PacketPlayOutUpdateAttributes", 44),
		MAP_CHUNK("PacketPlayOutMapChunk", 51),
		MULTI_BLOCK_CHANGE("PacketPlayOutMultiBlockChange", 52),
		BLOCK_CHANGE("PacketPlayOutBlockChange", 53),
		BLOCK_ACTION("PacketPlayOutBlockAction", 54),
		BLOCK_BREAK_ANIMATION("PacketPlayOutBlockBreakAnimation", 55),
		MAP_CHUNK_BULK("PacketPlayOutMapChunkBulk", 56),
		EXPLOSION("PacketPlayOutExplosion", 60),
		WORLD_EVENT("PacketPlayOutWorldEvent", 61),
		NAMED_SOUND_EFFECT("PacketPlayOutNamedSoundEffect", 62),
		WORLD_PARTICLES("PacketPlayOutWorldParticles", 63),
		GAME_STATE_CHANGE("PacketPlayOutGameStateChange", 70),
		SPAWN_ENTITY_WEATHER("PacketPlayOutSpawnEntityWeather", 71),
		OPEN_WINDOW("PacketPlayOutOpenWindow", 100),
		CLOSE_WINDOW("PacketPlayOutCloseWindow", 101),
		SET_SLOT("PacketPlayOutSetSlot", 103),
		WINDOW_ITEMS("PacketPlayOutWindowItems", 104),
		CRAFT_PROGRESS_BAR("PacketPlayOutCraftProgressBar", 105),
		TRANSACTION("PacketPlayOutTransaction", 106),
		UPDATE_SIGN("PacketPlayOutUpdateSign", 130),
		MAP("PacketPlayOutMap", 131),
		TILE_ENTITY_DATA("PacketPlayOutTileEntityData", 132),
		OPEN_SIGN_EDITOR("PacketPlayOutOpenSignEditor", 133),
		STATISTIC("PacketPlayOutStatistic", 200),
		PLAYER_INFO("PacketPlayOutPlayerInfo", 201),
		ABILITIES("PacketPlayOutAbilities", 202),
		TAB_COMPLETE("PacketPlayOutTabComplete", 203),
		SCOREBOARD_OBJECTIVE("PacketPlayOutScoreboardObjective", 206),
		SCOREBOARD_SCORE("PacketPlayOutScoreboardScore", 207),
		SCOREBOARD_DISPLAY_OBJECTIVE("PacketPlayOutScoreboardDisplayObjective", 208),
		SCOREBOARD_TEAM("PacketPlayOutScoreboardTeam", 209),
		CUSTOM_PAYLOAD("PacketPlayOutCustomPayload", 250),
		KICK_DISCONNECT("PacketPlayOutKickDisconnect", 255);

		private String className;
		private Class<?> packetClass;
		private ClassAccessor accessor;
		private int legacy;
		private ArrayList<PacketListener> listener;

		Server(String className, int legacy) {
			this.className = className;
			this.legacy = legacy;
			this.packetClass = ReflectionUtil.getMinecraftClass(className);
			if(this.packetClass != null){
				this.accessor = new ClassAccessor(this.packetClass);
			}
			this.listener = new ArrayList<>();
		}

		@Override
		public String getPacketClassName() {
			return className;
		}

		@Override
		public Class<?> getPacketClass() {
			return packetClass;
		}
		
		@Override
		public ClassAccessor getClassAccessor(){
			return accessor;
		}

		@Override
		public int getLegacyId() {
			return legacy;
		}

		@Override
		public ArrayList<PacketListener> getPacketListener() {
			return listener;
		}
	}
}
