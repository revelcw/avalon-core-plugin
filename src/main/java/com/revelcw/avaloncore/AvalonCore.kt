package com.revelcw.avaloncore

import co.aikar.commands.PaperCommandManager
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.revelcw.avaloncore.utils.ChatFormat
import org.bson.Document
import org.bukkit.plugin.java.JavaPlugin


class AvalonCore : JavaPlugin() {

  override fun onEnable() {
    val manager = PaperCommandManager(this)
    manager.registerCommand(LinkCommand())
//        val player = players.find(and(eq("linkCode", "J19-62H"), not(exists("minecraftUuid")))).first()
//        val updateData = set("minecraftUuid", "ec4ddf34-3935-4b20-b7b6-5d94f92a2db6")
//        val filter = and(eq("linkCode", "J19-62H"), not(exists("minecraftUuid")))
//        val update = players.findOneAndUpdate(filter, updateData)
//        if (update != null) {
//
//        }
////        println(collection.toString())
//        Bukkit.getServer().consoleSender.sendMessage(PREFIX + "Loaded Successfully!")
//        Bukkit.getServer().consoleSender.sendMessage(PREFIX + update)

  }

  override fun onDisable() {
    // Plugin shutdown logic
  }

  companion object {
    //    public static String prefix = "[" + ChatColor.RED + "A" + ChatColor.YELLOW + ":" + ChatColor.LIGHT_PURPLE + "C" + ChatColor
    private val mongoClient =
      MongoClient(MongoClientURI("mongodb+srv://bot:5yl1o5JTMV2FA7GV@cluster0.pux9z.mongodb.net/?retryWrites=true&w=majority"))
    private val database = mongoClient.getDatabase("avalon-project")
    val players: MongoCollection<Document> = database.getCollection("players")
  }
}