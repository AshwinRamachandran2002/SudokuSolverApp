from django.shortcuts import render

# Create your views here.
## Views.py
from django.shortcuts import render
#from scipy.misc.pilutil import imread, imresize
import numpy as np
import re
import sys
import os
sys.path.append(os.path.abspath("./model"))
#from .utils import *
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
global model, graph
model, graph = init()
import base64

OUTPUT = os.path.join(os.path.dirname(__file__), 'output.png')
from PIL import Image
from io import BytesIO
def getI420FromBase64(codec):
    base64_data = re.sub('^data:image/.+;base64,', '', codec)
    byte_data = base64.b64decode(base64_data)
    image_data = BytesIO(byte_data)
    img = Image.open(image_data)
    img.save(OUTPUT)
def convertImage(imgData):
    getI420FromBase64(imgData)
@csrf_exempt
def predict(request):
    imgData = request.POST.get('img')
    convertImage(imgData)
    x = imread(OUTPUT, mode='L')
    x = np.invert(x)
    x = imresize(x, (28, 28))
    x = x.reshape(1, 28, 28, 1)
    with graph.as_default():
        out = model.predict(x)
        print(out)
        print(np.argmax(out, axis=1))
        response = np.array_str(np.argmax(out, axis=1))
        return JsonResponse({"output": response})


def index(request):
    return render(request, 'index.html', {})
# urls.py
from django.urls import path
from .views import index
urlpatterns = [
    path('', index, name="index")
]