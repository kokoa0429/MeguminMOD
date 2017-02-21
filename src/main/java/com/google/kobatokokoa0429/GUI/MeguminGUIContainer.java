package com.google.kobatokokoa0429.GUI;

import javax.annotation.Nonnull;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Timer;

import com.kamesuta.mc.bnnwidget.WBase;
import com.kamesuta.mc.bnnwidget.WEvent;
import com.kamesuta.mc.bnnwidget.WFrame;
import com.kamesuta.mc.bnnwidget.WPanel;
import com.kamesuta.mc.bnnwidget.component.MChatTextField;
import com.kamesuta.mc.bnnwidget.font.FontSet;
import com.kamesuta.mc.bnnwidget.font.FontStyle;
import com.kamesuta.mc.bnnwidget.font.TrueTypeFont;
import com.kamesuta.mc.bnnwidget.font.WFont;
import com.kamesuta.mc.bnnwidget.font.WFontRenderer;
import com.kamesuta.mc.bnnwidget.motion.Easings;
import com.kamesuta.mc.bnnwidget.position.Area;
import com.kamesuta.mc.bnnwidget.position.Coord;
import com.kamesuta.mc.bnnwidget.position.Point;
import com.kamesuta.mc.bnnwidget.position.R;
import com.kamesuta.mc.bnnwidget.render.OpenGL;
import com.kamesuta.mc.bnnwidget.render.WRenderer;
import com.kamesuta.mc.bnnwidget.var.V;
import com.kamesuta.mc.bnnwidget.var.VCommon;
import com.kamesuta.mc.bnnwidget.var.VMotion;

import net.minecraft.client.gui.GuiScreen;

public class MeguminGUIContainer extends WFrame {


	private @Nonnull WFont trueTypeFont;
	private @Nonnull WFontRenderer fontRenderer;

	{
		setFixGuiScale(true);
		this.trueTypeFont = new TrueTypeFont(new FontStyle.Builder().setFont(new FontSet.Builder().addName("A-OTF 新ゴ Pro L").build()).build());
		this.fontRenderer = new WFontRenderer(this.trueTypeFont);
		Keyboard.enableRepeatEvents(true);
	}

	public MeguminGUIContainer() {
		super();
	}

	public MeguminGUIContainer(final GuiScreen parent) {
		super(parent);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}

	// init widgets in "initWidget"
	@Override
	protected void initWidget() {
		setGuiPauseGame(false);

		// add your panel extends "WPanel"
		add(new WPanel(new R()) {
			private float w = 100, h = 15;

			@Override
			protected void initWidget() {
				// "R" is a class that relatively expresses an area.
				add(new WBase(new R()) {
					// "VMotion" is a constantly changing value depending on the specified animation.
					VMotion m = V.pm(0).add(Easings.easeLinear.move(.25f, .25f)).start();

					@Override
					public void draw(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point p, final float frame, final float opacity) {
						Timer.tick();
						// Let's prepare before rendering with "WRenderer.startShape()"
						WRenderer.startShape();
						// BnnWidget provides a GL wrapper that absorbs differences in Minecraft versions.
						// The value of the "V" class is retrieved by get () every time.
						OpenGL.glColor4f(0f, 0f, 0f, this.m.get());
						// "WGui" has drawing methods that can be specified in detail with a float value.
						draw(getGuiPosition(pgp));
					}

					//private boolean mouseinside;

					@Override
					public void update(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point p) {
						// The area of the parent widget becomes get Area of the child widget by getGuiPosition.
						//final Area a = getGuiPosition(pgp);
						// PointInside determines if the cursor is over the widget
						//						if (a.pointInside(p)) {
						//							if (!this.mouseinside) {
						//								this.mouseinside = true;
						//								// Add animation and start it. Release the queue with stop.
						//								this.m.stop().add(Easings.easeLinear.move(.2f, 0f)).start();
						//							}
						//						} else if (this.mouseinside) {
						//							this.mouseinside = false;
						//							this.m.stop().add(Easings.easeLinear.move(.2f, .5f)).start();
						//						}
						super.update(ev, pgp, p);
					}

					// It is called when the GUI is closed. If you want to add closing animation, return false and let's wait.
					@Override
					public boolean onCloseRequest() {
						this.m.stop().add(Easings.easeLinear.move(0.25f, 0f));
						return false;
					}

					// The GUI will not exit until it returns true. Let's see if the animation has ended with isFinished.
					@Override
					public boolean onClosing(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point mouse) {
						return this.m.isFinished();
					}
				});


				final MChatTextField text = new MChatTextField(new R(Coord.left(10), Coord.right(10), Coord.bottom(10), Coord.height(200))){


					@Override
					public void draw(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point p, final float frame, final float opacity) {

						final Area a = getGuiPosition(pgp);
						updateArea(a);
						final int x = this.t.xPosition;
						final int y = this.t.yPosition;
						final int w = this.t.width;
						final int h = this.t.height;
						this.t.xPosition = 1;
						this.t.yPosition = 1;
						this.t.width = (int) a.w()-2;
						this.t.height = (int) a.h()-2;
						OpenGL.glPushMatrix();
						OpenGL.glTranslatef(a.x1(), a.y1(), 0f);

						

						this.t.drawTextBox();

						OpenGL.glPopMatrix();
						this.t.xPosition = x;
						this.t.yPosition = y;
						this.t.width = w;
						this.t.height = h;
					}



				};
				text.setMaxStringLength(1000);
				add(text);




				final R box = new R(Coord.left(5), Coord.top(5),
						Coord.width(new VCommon() {
							@Override
							public float getAbsCoord(final float a, final float b) {
								return get();
							}

							@Override
							public float get() {
								return w;
							}
						}),
						Coord.height(new VCommon() {
							@Override
							public float getAbsCoord(final float a, final float b) {
								return get();
							}

							@Override
							public float get() {
								return h;
							}
						}));
				//new R(Coord.width(200), Coord.height(200), Coord.pleft(.5f), Coord.ptop(.5f)).child(Coord.pleft(-.5f), Coord.ptop(-.5f))


				/*
				add(new WPanel(box) {
					@Override
					protected void initWidget() {
						super.initWidget();
					}

					private FontPosition f = new FontPosition();

					private @Nullable Point point;

					private float wid;
					private float hei;

					@Override
					public boolean mouseClicked(final WEvent ev, final Area pgp, final Point p, final int button) {
						this.point = p;
						this.wid = w;
						this.hei = h;
						return super.mouseClicked(ev, pgp, p, button);
					}

					@Override
					public boolean mouseDragged(final WEvent ev, final Area pgp, final Point p, final int button, final long time) {
						final Point point = this.point;
						if (point!=null) {
							w = this.wid-point.x()+p.x();
							h = this.hei-point.y()+p.y();
						}
						return super.mouseDragged(ev, pgp, p, button, time);
					}

					@Override
					public void draw(final WEvent ev, final Area pgp, final Point p, final float frame, final float popacity) {
						final Area a = getGuiPosition(pgp);
						WRenderer.startShape();
						OpenGL.glLineWidth(.5f);
						OpenGL.glColor4f(1f, 1f, 1f, 1f);
						draw(a, GL11.GL_LINE_LOOP);

						WRenderer.startTexture();
						OpenGL.glColor4f(13f, 0f, 0f, 1f);
						// fontRenderer.drawString(this.f.setPosition(a.w(), 0).setText(text.getText().replace("&", "§").replace("\\n", "\n")).setScale(.5f, .5f).setAlign(Align.RIGHT));
						MeguminGUIContainer.this.fontRenderer.drawString(text.getText().replace("&", "§").replace("\\n", "\n"), a, 1f, Align.LEFT);
						super.draw(ev, pgp, p, frame, popacity);
					}
				});
				*/

			}
		});
	}



}