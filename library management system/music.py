import pygame
import sys
import logging
import tkinter as tk
from tkinter import filedialog, messagebox

# Initialize logging
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')

class MusicPlayer:
    def __init__(self, root):
        self.root = root
        self.root.title("Music Player")
        self.root.geometry("400x300")

        self.paused = False
        self.stopped = True
        self.file = None

        # Initialize the mixer module in pygame
        pygame.mixer.init()
        logging.info("Pygame mixer initialized")

        # Create GUI components
        self.label = tk.Label(self.root, text="No music loaded", font=("Helvetica", 12))
        self.label.pack(pady=20)

        button_frame = tk.Frame(self.root)
        button_frame.pack(pady=20)

        self.play_button = tk.Button(button_frame, text="Play", command=self.play_music, state=tk.DISABLED, width=10)
        self.play_button.pack(side=tk.LEFT, padx=10)

        self.pause_button = tk.Button(button_frame, text="Pause", command=self.pause_music, state=tk.DISABLED, width=10)
        self.pause_button.pack(side=tk.LEFT, padx=10)

        self.resume_button = tk.Button(button_frame, text="Resume", command=self.resume_music, state=tk.DISABLED, width=10)
        self.resume_button.pack(side=tk.LEFT, padx=10)

        self.stop_button = tk.Button(button_frame, text="Stop", command=self.stop_music, state=tk.DISABLED, width=10)
        self.stop_button.pack(side=tk.LEFT, padx=10)

        self.load_button = tk.Button(self.root, text="Load Music", command=self.load_music, width=30)
        self.load_button.pack(pady=10)

        self.progress_label = tk.Label(self.root, text="", font=("Helvetica", 10))
        self.progress_label.pack(pady=10)

        self.root.protocol("WM_DELETE_WINDOW", self.on_closing)

    def load_music(self):
        self.file = filedialog.askopenfilename(filetypes=[("MP3 files", "*.mp3")])
        if self.file:
            self.label.config(text=f"Loaded: {self.file}")
            self.play_button.config(state=tk.NORMAL)
            self.pause_button.config(state=tk.DISABLED)
            self.resume_button.config(state=tk.DISABLED)
            self.stop_button.config(state=tk.DISABLED)
            self.stopped = True
            logging.info(f"Loaded music file: {self.file}")

    def play_music(self):
        if self.file:
            try:
                pygame.mixer.music.load(self.file)
                pygame.mixer.music.play()
                self.paused = False
                self.stopped = False
                logging.info("Music playback started")
                self.update_progress()
                self.play_button.config(state=tk.DISABLED)
                self.pause_button.config(state=tk.NORMAL)
                self.stop_button.config(state=tk.NORMAL)
            except pygame.error as e:
                logging.error(f"Could not load music file: {e}")
                messagebox.showerror("Error", f"Could not load music file: {e}")
                sys.exit(1)

    def pause_music(self):
        if not self.paused:
            pygame.mixer.music.pause()
            self.paused = True
            logging.info("Music paused")
            self.pause_button.config(state=tk.DISABLED)
            self.resume_button.config(state=tk.NORMAL)

    def resume_music(self):
        if self.paused:
            pygame.mixer.music.unpause()
            self.paused = False
            logging.info("Music resumed")
            self.pause_button.config(state=tk.NORMAL)
            self.resume_button.config(state=tk.DISABLED)

    def stop_music(self):
        pygame.mixer.music.stop()
        self.stopped = True
        logging.info("Music stopped")
        self.play_button.config(state=tk.NORMAL)
        self.pause_button.config(state=tk.DISABLED)
        self.resume_button.config(state=tk.DISABLED)
        self.stop_button.config(state=tk.DISABLED)
        self.progress_label.config(text="")

    def update_progress(self):
        if pygame.mixer.music.get_busy():
            current_pos = pygame.mixer.music.get_pos() / 1000.0
            self.progress_label.config(text=f"Current Position: {current_pos:.2f} seconds")
            self.root.after(1000, self.update_progress)
        else:
            self.progress_label.config(text="")

    def on_closing(self):
        pygame.mixer.music.stop()
        self.root.destroy()

if __name__ == "__main__":
    root = tk.Tk()
    player = MusicPlayer(root)
    root.mainloop()
