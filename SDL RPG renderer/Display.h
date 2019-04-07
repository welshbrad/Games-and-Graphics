#ifndef DISPLAY_H
#define DISPLAY_H

#include <SDL2\SDL.h>
#define MAX_TEXTURES 10 //TODO: Add dynamic allocation

//#define FULLSCREEN
#ifndef DEBUG_ON
#define RENDERER_FLAGS SDL_RENDERER_PRESENTVSYNC
#endif
#ifdef FULLSCREEN
#define SDL_SCREEN_MODE SDL_WINDOW_FULLSCREEN
#define DISPLAY_WIDTH 1920
#define DISPLAY_HEIGHT 1080
#endif //if FULLSCREEN == true
#ifndef FULLSCREEN
#define SDL_SCREEN_MODE 0
#define DISPLAY_HEIGHT 800
#define DISPLAY_WIDTH (DISPLAY_HEIGHT * 16 / 9)
#endif //if FULLSCREEN == false

struct RGBcolor;
class Display{

public:
	Display();

	int working = 0;
	void init_SDL();
	void loadTextures();
	int isValid();
	void cleanup();
	void draw();
	void drawOverlay(SDL_Rect dest, SDL_Texture* tex, SDL_Rect src, float rotation);
	//int getScreenWidth(){ return 800; }; //Todo: Implement
	//int getScreenHeight(){ return ; }; //Todo: Implement

	int processEvents(SDL_Window *window);

	bool begin();
	void clear();

	SDL_Window *getSDLWindow(){
		return window;
	}
	SDL_Renderer *getSDLRenderer(){
		return m_SDLrenderer;
	}
	SDL_DisplayMode getCurrentDisplayMode(){
		return m_currentDisplayMode;
	}

	~Display();
protected:
private:
	SDL_DisplayMode m_currentDisplayMode;
	SDL_Window *window;
	SDL_Renderer *m_SDLrenderer;
	SDL_RendererFlip m_flip;

	RGBcolor* standardModulation;

};

struct RGBcolor{
	Uint8 r;
	Uint8 g;
	Uint8 b;
};

#endif