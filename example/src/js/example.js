import { ExoPlayer } from 'exoplayer-capacitor';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    ExoPlayer.echo({ value: inputValue })
}
