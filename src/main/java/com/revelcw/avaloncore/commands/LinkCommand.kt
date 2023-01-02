package com.revelcw.avaloncore.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import com.mongodb.client.model.Filters.*
import com.revelcw.avaloncore.AvalonCore.Companion.datastore
import com.revelcw.avaloncore.entity.LinkCode
import com.revelcw.avaloncore.utils.ChatFormat
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandAlias("link")
class LinkCommand : BaseCommand() {
  @Default
  fun link(player: CommandSender, code: String): Boolean {
    if (player !is Player) {
      ChatFormat.sendError(player, "You must run this command as a player!")
      return true
    }
    val playerData = datastore.fetchUserByMinecraft(player.uniqueId.toString())
    if (playerData != null) {
      ChatFormat.sendWarning(player, "You have already linked a Discord account! (${playerData["discordId"]})")
      return true
    }
    if (!LinkCode.isValid(code)) {
      ChatFormat.sendWarning(
        player,
        "Invalid link code format. Make sure your link code looks like this: XXX-XXX"
      )
      return true
    }

    val linkCode = LinkCode(code)
    val updateResult = datastore.linkAccount(linkCode, player.uniqueId.toString())
    println(updateResult)
    if (updateResult != null) {
      ChatFormat.sendSuccess( player, "Successfully linked account to ${updateResult["discordId"]}")
      return true;
    } else {
      ChatFormat.sendWarning(player, "Link code doesn't exist! Generate a new one in discord by running /link.")
    }
    return true
  }
}