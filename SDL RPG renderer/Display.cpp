#include "Display.h"
#include "Main.h"
#include "Keyboard.h"



Display::Display(){
	working = 1;
	init_SDL();
}

//start up SDL and create window given SDL_Renderer 
void Display::init_SDL(){
	standardModulation = new RGBcolor();
	standardModulation->r = 0xff;
	standardModulation->g = 0xff;
	standardModulation->b = 0xff;

	working = 1;
	m_flip = SDL_FLIP_NONE;
	SDL_Init(SDL_INIT_VIDEO);
	


	window = SDL_CreateWindow(
		"RPG",                  // window title
		SDL_WINDOWPOS_UNDEFINED,           // initial x position
		SDL_WINDOWPOS_UNDEFINED,           // initial y position
		DISPLAY_WIDTH,                               // width, in pixels
		DISPLAY_HEIGHT,                               // height, in pixels
		SDL_SCREEN_MODE         
		);

	m_SDLrenderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED | SDL_RENDERER_TARGETTEXTURE
#ifdef RENDERER_FLAGS
		| RENDERER_FLAGS
#endif
	);


	SDL_GetWindowDisplayMode(window, &m_currentDisplayMode);
	std::cout << m_currentDisplayMode.w << " " << m_currentDisplayMode.h << std::endl;

	//SDL_RenderSetLogicalSize(m_SDLrenderer, 320, 240);
	SDL_SetRenderDrawColor(m_SDLrenderer, 0x64, 0x85, 0x93, 0x00);
}

void Display::draw(){
	SDL_RenderPresent(m_SDLrenderer);

}

void Display::drawOverlay(SDL_Rect dest, SDL_Texture* tex, SDL_Rect src, float rotation){
	SDL_SetTextureColorMod(tex, standardModulation->r, standardModulation->g, standardModulation->b);
	SDL_SetRenderTarget(m_SDLrenderer, tex);
	//SDL_RenderCopy(m_SDLrenderer, tex, &src, &dest
	SDL_RenderCopyEx(m_SDLrenderer, tex, &src, &dest, rotation, NULL, m_flip);
}

int Display::isValid(){
	if (window == NULL) {
		// In the case that the window could not be made...
		printf("Could not create window: %s\n", SDL_GetError());
		return 0;
	}
	return 1;
}

void Display::cleanup(){
	SDL_DestroyWindow(window);
	SDL_DestroyRenderer(m_SDLrenderer);
	SDL_Quit();
}



void Display::loadTextures(){
	//Render::texture0 = loadTextureSheet("res/hud.0.png", renderer);
}

bool Display::begin(){
	//resetKeyboard();
	if (processEvents(window))
		return true;
	else
		return false;
}

void Display::clear(){
	SDL_RenderClear(m_SDLrenderer);
}

//Handle keyboard input, window scaling, and quit events.
int Display::processEvents(SDL_Window* window){
	SDL_Event event;
	bool quit = false;
	//Process Keyboard Events
	

	while (SDL_PollEvent(&event) && window != NULL) {
		processKeyEvents(&event);
		switch (event.type) {
		case SDL_WINDOWEVENT_CLOSE: {
			if (window) {
				SDL_DestroyWindow(window);
				window = NULL;
				quit = true;
			}
		} break;
		case SDL_KEYDOWN:  {
			switch (event.key.keysym.sym) {
			case SDLK_ESCAPE:
				quit = true;
				break;
			case SDLK_SPACE:
				break;
			}
		}break;
		case SDL_QUIT:
			quit = true;
			break;
		}
	}
	if (quit || window == NULL)
		return true;
	else
		return false;
}

Display::~Display(){

}
