package YaArts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *A Simple Pixel Art Plugin~
 *
 *@author SNaKe
 */
public final class Pixelable extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("插件加载成功-Credit By:SNaKe");
    }

    @Override
    public void onDisable() {
        getLogger().info("Welcome to Use");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command Pixel, String label, String[] args) {
        if (Pixel.getName().equalsIgnoreCase("art")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("该命令必须由玩家执行，不要瞎玩，在后台你是看不到的");
                return true;
            }
            Player player = (Player) sender;
            //获得玩家三轴坐标
            int x = (int)player.getLocation().getX()+50;
            int y = (int)player.getLocation().getY();
            //难解y轴
            int z = (int)player.getLocation().getZ();
            World world = player.getWorld();
            String arg = args[0];
            int arg2=Integer.parseInt(args[1]);
            getLogger().info(arg);
            File file = new File(arg);
            BufferedImage bufImg = null;
            try {
                bufImg = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int height = bufImg.getHeight();
            int width = bufImg.getWidth();
            int point;
            for(int zindx=z;zindx<z+width;zindx++){
                for(int yindx=y;yindx<y+height;yindx++){
                    point=bufImg.getRGB(zindx-z,(height-1)-(yindx-y))&0x0000ff;
                    getLogger().info(point+"");
                    Block currentBlock = world.getBlockAt(x+10, yindx,zindx);
                    if(point>=arg2){
                        currentBlock.setType(Material.SNOW_BLOCK);
                    }
                    else{
                        currentBlock.setType(Material.COAL_BLOCK);
                    }
                }
            }
            return true;
        }
        return false;
    }


}
