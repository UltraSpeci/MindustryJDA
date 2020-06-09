# THIS PLUGIN IS OUTDATED
This plugin have not been updated recently and will NOT work on Mindustry 5.0 build 102 or later.
Feel free to clone and open a pull request to update it if you want to. Theres no planned update in the near future from me.

# MindustryJDA

This is a Work-In-Progress plugin made to sync chat between Discord <-> Mindustry.
This plugin uses JDA.

It is not recommended that you use this plugin for non-testing purposes!
Bugs are present. Consider creating a new issue for bugs that you find!
it will greatly help me!

# How to Use
Step 1. Download the latest release from the releases tab.

Step 2. Put the plugin jar file into the "config/mods" folder

Step 3. Turn on the server. 
  MindustryJDA will tell you that MindustryJDA could not login.
  Close the server.

Step 4. Create a new discord bot account
  You can do this by going to https://discordapp.com/developers
  and making a new application, then go to the Bot section and create a new bot account.

Step 5. Inviting the bot to your server.
  in the Bot section, click on the "Copy" button for the Bot-Token!
  then, put in this link
  https://discordapp.com/api/oauth2/authorize?client_id=(bot-token)&permissions=8&scope=bot
  and replace "(bot-token)" with the token you have just copied!
  
  Note: The bot will be given the administrator permission! I (blackassasin1234) does not access your bot in anyway!

Step 6. Configuring MindustryJDA
  Simply go to the config.yaml file in the "config/mods/MindustryJDA" folder and replace the bot-token
  with your bot token!
  
  To set the channel which MindustryJDA will be using, enable developer mode for discord if you havent done so in the 
  discord settings, then right-click the channel you want to use for the chat and click on "Copy ID"
  and then replace the Channel ID in the MindustryJDA config.

Step 7. Start the server!
  Yes thats it! your done!
