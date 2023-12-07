# Given a texture atlas of every card in a deck, separate into individual files
# Found a nice looking image from wikipedia, but the cards are not densely packed
# There is a fairly large green margin between cards which would be an eyesore
# Iterates over a 4x13 grid, cropping 356x535 px area and skipping 33 px margin

from io import BytesIO
from PIL import Image

texture_atlas = Image.open("assets/atlas.png").convert('RGBA')

y = 33
for i in range(0, 4):
    x = 33
    for j in range(0, 13):
        chunk = texture_atlas.crop(
            x, y,
            x + 356, y + 535
        )
        chunk.save(f"assets/cards/card{i}-{j}.png")
        x += 389
    y += 570

