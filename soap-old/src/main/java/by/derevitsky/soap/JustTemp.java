package by.derevitsky.soap;

import org.springframework.stereotype.Component;

@Component
public class JustTemp {

    public String getTestMessage() {
        return "Just testin autowiring...";
    }
}
