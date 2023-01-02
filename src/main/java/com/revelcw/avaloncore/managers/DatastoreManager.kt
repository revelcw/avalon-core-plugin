package com.revelcw.avaloncore.managers

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.*
import com.mongodb.client.model.Updates
import com.revelcw.avaloncore.entity.LinkCode
import org.bson.Document
import org.bson.conversions.Bson

class DatastoreManager(mongoClient: MongoClient) {
  private val database = mongoClient.getDatabase("avalon-project")
  private val players: MongoCollection<Document> = database.getCollection("players")

  fun fetchUserByMinecraft(uuid: String): Document? {
    return players.find(eq("minecraftUuid", uuid)).first()
  }

  fun updateUserByMinecraft(uuid: String, newData: Bson): Document? {
    return players.findOneAndUpdate(eq("minecraftUuid", uuid), newData)
  }

  //Ideally works, but not tested!

//  fun fetchUserByDiscord(id: String): Document? {
//    return players.find(eq("discordId", id)).first()
//  }
//
//  fun updateUserByDiscord(id: String, newData: Bson): Document? {
//    return players.findOneAndUpdate(eq("discordId", id), newData)
//  }

  fun linkAccount(linkCode: LinkCode, player: String): Document? {
    val filter = and(eq("linkCode", linkCode.strippedCode), not(exists("minecraftUuid")))
    val updateData = and(Updates.set("minecraftUuid", player), Updates.unset("linkCode"))
    return players.findOneAndUpdate(filter, updateData);
  }

}