package my.org;

import eye.Eye;
import eye.Match;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.BeforeExecution;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


@GraphWalker(value = "random(edge_coverage(100))", start = "v_ClientNotRunning")
public class SomeSmallTest extends ExecutionContext implements Login {

    private Eye eye;

    @BeforeExecution
    public void setup() {
        eye = new Eye();
    }


    @Override
    public void v_ClientNotRunning() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BufferedImage image = getBufferedImage("/images/app_running.png");
        eye.setTimeout(2);
        Match match = eye.findImage(image);
        eye.setTimeout(30);
        Assert.assertNull("Did not to find a match app_running.png", match);
    }

    @Override
    public void e_ToggleRememberMe() {
        BufferedImage image = getBufferedImage("/images/remember_me.png");
        Match match = eye.findImage(image);
        Assert.assertTrue("Could not toggle remember me", eye.click(match.getCenterLocation()));
    }

    @Override
    public void e_ValidPremiumCredentials() {
        BufferedImage image = getBufferedImage("/images/remember_me.png");
        Match match = eye.findImage(image);
        Assert.assertTrue("Could find the password field", eye.doubleClick(match.getCenterRelativeLocation(0, -30)));
        eye.type("YOUR SECRET PASSWORD");

        image = getBufferedImage("/images/login_btn.png");
        match = eye.findImage(image);
        Assert.assertTrue("Could not click on login button", eye.click(match.getCenterLocation()));
    }

    @Override
    public void v_Browse() {
        BufferedImage image = getBufferedImage("/images/krikar_profile.png");
        Assert.assertNotNull("Could not find krikar profile", eye.findImage(image));
    }

    @Override
    public void e_Logout() {
        BufferedImage image = getBufferedImage("/images/krikar_profile.png");
        Match match = eye.findImage(image);
        Assert.assertTrue("Could not click on krikar profile", eye.click(match.getCenterRelativeLocation(60, 0)));

        image = getBufferedImage("/images/logout.png");
        match = eye.findImage(image);
        Assert.assertTrue("Could not click on logout in menu", eye.click(match.getCenterLocation()));
    }

    @Override
    public void v_LoginPrompted() {
        BufferedImage image = getBufferedImage("/images/login.png");
        Assert.assertNotNull("Could not find the login window", eye.findImage(image));
    }

    @Override
    public void e_StartClient() {
        BufferedImage image = getBufferedImage("/images/spotify_desktop_icon.png");
        Match match = eye.findImage(image);
        Assert.assertTrue("Could not click on Spotify desktop icon", eye.click(match.getCenterLocation()));
    }

    @Override
    public void e_Close() {
        BufferedImage image = getBufferedImage("/images/app_running.png");
        Match match = eye.findImage(image);
        Assert.assertTrue("Could not right click on task bar icon", eye.contextClick(match.getCenterLocation()));

        image = getBufferedImage("/images/close.png");
        match = eye.findImage(image);
        Assert.assertTrue("Could not click on close in context menu", eye.contextClick(match.getCenterLocation()));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e_Exit() {
        BufferedImage image = getBufferedImage("/images/app_running.png");
        Match match = eye.findImage(image);
        Assert.assertTrue("Could not right click on task bar icon", eye.contextClick(match.getCenterLocation()));

        image = getBufferedImage("/images/close.png");
        match = eye.findImage(image);
        Assert.assertTrue("Could not click on close in context menu", eye.contextClick(match.getCenterLocation()));
    }

    @Override
    public void e_InvalidCredentials() {
        BufferedImage image = getBufferedImage("/images/remember_me.png");
        Match match = eye.findImage(image);
        Assert.assertTrue("Could find the password field", eye.doubleClick(match.getCenterRelativeLocation(0, -30)));
        eye.type("bla bla bla");

        image = getBufferedImage("/images/login_btn.png");
        match = eye.findImage(image);
        Assert.assertTrue("Could not click on login button", eye.click(match.getCenterLocation()));
    }

    private BufferedImage getBufferedImage(String fileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            Assert.fail("Could not load image: " + fileName + " Caused by: " + e.getMessage());
        }
        return image;
    }

}
