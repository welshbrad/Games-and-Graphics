#ifndef DISPLAY_H
#define DISPLAY_H

#include <SDL2\SDL.h>
#define MAX_TEXTURES 10 //TODO: Add dynamic allocation

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
	int getScreenWidth(){ return 1920; }; //Todo: Implement
	int getScreenHeight(){ return 1080; }; //Todo: Implement

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