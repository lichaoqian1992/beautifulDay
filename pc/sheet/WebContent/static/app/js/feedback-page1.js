(function(mui, window, document, undefined) {
	mui.init();
	var get = function(id) {
		return document.getElementById(id);
	};
	var qsa = function(sel) {
		return [].slice.call(document.querySelectorAll(sel));
	};
	var ui = {
		question: get('question'),
		contact: get('contact'),
		imageList: get('image-list'),
		//		submit: get('submit')
	};
	ui.clearForm = function() {
		ui.question.value = '';
		ui.contact.value = '';
		ui.imageList.innerHTML = '';
		ui.newPlaceholder();
	};
	ui.getFileInputArray = function() {
		return [].slice.call(ui.imageList.querySelectorAll('input[type="file"]'));
	};
	ui.getFileInputIdArray = function() {
		var fileInputArray = ui.getFileInputArray();
		var idArray = [];
		fileInputArray.forEach(function(fileInput) {
			if(fileInput.value != '') {
				idArray.push(fileInput.getAttribute('id'));
			}
		});
		return idArray;
	};
	var imageIndexIdNum = 0;
	ui.newPlaceholder = function() {
		var fileInputArray = ui.getFileInputArray();
		if(fileInputArray &&
			fileInputArray.length > 0 &&
			fileInputArray[fileInputArray.length - 1].parentNode.classList.contains('space')) {
			return;
		}

		imageIndexIdNum++;
		var placeholder = document.createElement('div');
		placeholder.setAttribute('class', 'image-item space');
		var closeButton = document.createElement('div');
		closeButton.setAttribute('class', 'image-close');
		closeButton.innerHTML = 'X';
		closeButton.addEventListener('click', function(event) {
			event.stopPropagation();
			event.cancelBubble = true;
			setTimeout(function() {
				ui.imageList.removeChild(placeholder);
			}, 0);
			return false;
		}, false);

		//    用于压缩图片的canvas
		var canvas = document.createElement("canvas");
		var ctx = canvas.getContext('2d');
		//    瓦片canvas
		var tCanvas = document.createElement("canvas");
		var tctx = tCanvas.getContext("2d");
		var maxsize = 100 * 1024;

		var fileInput = document.createElement('input');
		fileInput.setAttribute('type', 'file');
		fileInput.setAttribute('name', 'file');
		fileInput.setAttribute('accept', 'image/*');
		fileInput.setAttribute('id', 'image-' + imageIndexIdNum);

		fileInput.addEventListener('change', function(event) {
			var file = fileInput.files[0];

			if(file) {

				//if(file.size > 1024 * 1024 * 2) {
					var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024)) / 10 + "MB" : ~~(file.size / 1024) + "KB";
					var reader = new FileReader();
					reader.onload = function() {

						//处理 android 4.1 兼容问题
						var base64 = reader.result.split(',')[1];
						var dataUrl = 'data:image/png;base64,' + base64;
						//
						var result = this.result;
						var img = new Image();
						img.src = result;
//						placeholder.style.backgroundImage = 'url(' + dataUrl + ')';
						placeholder.style.backgroundImage = 'url(' + result + ')';
						//如果图片大小小于100kb，则直接上传
						if(result.length <= maxsize) {
							img = null;
							// upload(result, file.type, $(li));
							return;
						}
						//      图片加载完毕之后进行压缩，然后上传
						if(img.complete) {
							callback();
						} else {
							img.onload = callback;
						}

						function callback() {
							var data = compress(img);
							// upload(data, file.type, $(li));
							img = null;
						}
					}
					reader.readAsDataURL(file, {
						maxWidth: 100,
						maxHeight: 100,
						quality: 0.1
					});
					placeholder.classList.remove('space');
					ui.newPlaceholder();
				//} else {
					//alert(22);
				//}
			}

		}, false);

		//    使用canvas对大图片进行压缩
		function compress(img) {
			var initSize = img.src.length;
			var width = img.width;
			var height = img.height;
			//如果图片大于四百万像素，计算压缩比并将大小压至400万以下
			var ratio;
			if((ratio = width * height / 4000000) > 1) {
				ratio = Math.sqrt(ratio);
				width /= ratio;
				height /= ratio;
			} else {
				ratio = 1;
			}
			canvas.width = width;
			canvas.height = height;
			//        铺底色
			ctx.fillStyle = "#fff";
			ctx.fillRect(0, 0, canvas.width, canvas.height);
			//如果图片像素大于100万则使用瓦片绘制
			var count;
			if((count = width * height / 1000000) > 1) {
				count = ~~(Math.sqrt(count) + 1); //计算要分成多少块瓦片
				//            计算每块瓦片的宽和高
				var nw = ~~(width / count);
				var nh = ~~(height / count);
				tCanvas.width = nw;
				tCanvas.height = nh;
				for(var i = 0; i < count; i++) {
					for(var j = 0; j < count; j++) {
						tctx.drawImage(img, i * nw * ratio, j * nh * ratio, nw * ratio, nh * ratio, 0, 0, nw, nh);
						ctx.drawImage(tCanvas, i * nw, j * nh, nw, nh);
					}
				}
			} else {
				ctx.drawImage(img, 0, 0, width, height);
			}
			//进行最小压缩
			var ndata = canvas.toDataURL('image/jpeg', 0.1);
			console.log('压缩前：' + initSize);
			console.log('压缩后：' + ndata.length);
			console.log('压缩率：' + ~~(100 * (initSize - ndata.length) / initSize) + "%");
			tCanvas.width = tCanvas.height = canvas.width = canvas.height = 0;
			return ndata;
		}
        //---------
		placeholder.appendChild(closeButton);
		placeholder.appendChild(fileInput);
		ui.imageList.appendChild(placeholder);
	};
	ui.newPlaceholder();
	//	ui.submit.addEventListener('tap', function(event) {
	//		if (ui.question.value == '' ||
	//			(ui.contact.value != '' &&
	//				ui.contact.value.search(/^(\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+)|([1-9]\d{4,9})$/) != 0)) {
	//			return mui.toast('信息填写不符合规范');
	//		} 
	//		plus.nativeUI.showWaiting();
	//		feedback.send({
	//			question: ui.question.value,
	//			contact: ui.contact.value,
	//			images: ui.getFileInputIdArray()
	//		}, function() {
	//			plus.nativeUI.closeWaiting();
	//			mui.toast('感谢您的建议~');
	//			ui.clearForm();
	//			mui.back();
	//		});
	//	}, false);
})(mui, window, document, undefined);