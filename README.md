<h1 align="center"> Figura Parser </h1>

A patched version of the Minecraft Figura Mod https://github.com/FiguraMC/Figura with a built in parser that transforms
the NBT-ised avatars back to their original parts: models, scripts, textures, and sounds.

All parsed avatars are to be found in `.minecraft/figura/avatars/downloaded/`

## DISCLAIMER

Use common scene,
redistributing and reposing other's avatars without consent is seriously not cool and childish, so please refrain from doing so.

Uploading yoinked avatars into the backend may count as redistribution and may lead into being banned from the backend.

On further note:
- I will not assist in removing DRM from avatars.
- I will not debug any script errors that are not related to the parser itself.
- You should not discuss or promote this tool on the Official Figura Discord as it is against the rules (please give the mods a break for once).


## Tools

### `/figura parse <PATH>`
A command that parses the local avatar which is inside of a `.moon` extension (NBT).
Path refers to a relative path inside of the `/figura` folder.

Example usage:
`/figura parse testAvatar.moon` would parse an avatar cache in `.minecraft/figura/testAvatar.moon`


### Downloading via the GUI

#### In the Permissions tab

You can press the Download Avatar option when right clicking a player.
![image](https://github.com/Buwwet/FiguraParser/assets/59463043/5de483ba-cdcd-4388-a677-43285a02dda3)

#### In the Help tab

You can download the avatar of a given player by using their username and pressing the "Download" button.
![image](https://github.com/Buwwet/FiguraParser/assets/59463043/c94e4804-d111-4100-bb0c-ae58190d36c3)


## Issues that you'll have to fix on a case by case basis.
- Meshes may be outputted with incorrect UVs in some extreme cases.
- Meshes will have a set of vertexes for each face. If you plan on re-working the mesh, you can fix this
by going into BlockBench and `Edit > Mesh > Merge Vertecies > Merge by Distance`. You should choose the smallest distance posible (as the vertices we want to merge are in the exact same position).

## How to compile

1. Download the repo into your computer (I recommend using git as you can then do git pull to update the code)
2. Open the `build.gradle` as a project in your preferred Java IDE (IntelliJ IDEA works well)
3. Let it automatically download all the required libraries and stuff.
4. Run the corresponding gradle build task for either Forge or Fabric.
5. Mod jar should be in `builds/libs` of the respective module.


If you have any questions, send a DM via Discord.
