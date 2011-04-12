if (!html5)
	var html5 = {}
if (!html5.jsf) {
	html5.jsf = {
		init : function(ccid, payloadType, renderIds) {
			var dropzone = $(ccid);

			if (dropzone.serverPayload) // already initialized
				return;
			
			dropzone.payloadInput = $(ccid + ":form:payload");
			dropzone.acceptDrop = false;
			dropzone.serverPayload = function() {
				return dropzone.payloadInput.value;
			};

			dropzone.addEventListener("drop", function(event) {
				if (payloadType == "")
					payloadType = "text";

				if (renderIds == "" || renderIds == "@this")
					renderIds = ccid;

				dropzone.payloadInput.value = event.dataTransfer
						.getData(payloadType);
				
				jsf.ajax.request(dropzone.payloadInput, event, {
					render: renderIds,
					onevent: function(event) {
						if (event.status == "success")
							html5.jsf.init(ccid, payloadType, renderIds);
					}
				});				
			}, false);

			dropzone.addEventListener("dragenter", function(event) {
				if (dropzone.acceptDrop)
					event.preventDefault();
			}, false);

			dropzone.addEventListener("dragover", function(event) {
				if (dropzone.acceptDrop)
					event.preventDefault();
			}, false);
		}
	};
}