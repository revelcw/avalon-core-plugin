package com.revelcw.avaloncore

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import com.mongodb.client.model.Filters.*
import com.mongodb.client.model.Updates.set
import com.revelcw.avaloncore.AvalonCore.Companion.players
import com.revelcw.avaloncore.entity.LinkCode
import com.revelcw.avaloncore.utils.ChatFormat
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandAlias("link")
class LinkCommand : BaseCommand() {

  @Default
  fun link(player: CommandSender, code: String): Boolean {
    if (player !is Player) {
      ChatFormat.sendMessage(player, "&redYou must run this command as a player!")
      return true
    }
    if (!LinkCode.isValid(code)) {
      ChatFormat.sendMessage(
        player,
        "Link code must be made of 6 characters with an optional dash. (ie. FJ0-67H, FJ067H)"
      )
      return true
    }
    val linkCode = LinkCode(code)
    ChatFormat.sendMessage(player, linkCode.dashedCode)
    val updateData = set("minecraftUuid", player.uniqueId.toString())
    val filter = and(eq("linkCode", linkCode.code), not(exists("minecraftUuid")))
    val updateResult = players.findOneAndUpdate(filter, updateData)
    if (updateResult != null) {
      ChatFormat.sendMessage(player, "Successfully linked account to ${updateResult["discordId"]}")
      return true;
    } else {
      val playerRecord = players.find(eq("minecraftUuid", player.uniqueId.toString())).first()
      if (playerRecord != null) {
        ChatFormat.sendMessage(player, "You have already linked a Discord account! (${playerRecord["discordId"]})")
      } else {
        ChatFormat.sendMessage(player, "Invalid link code. Generate a new one in discord by running /link")
      }
    }
    return true
  }
}