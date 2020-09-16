import { Component } from '@angular/core';
import { Platform } from '@ionic/angular';

// SCANDIT
declare var Scandit: any;

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  
  public ScanditApiKeyAndroid = "";
  public ScanditApiKeyIOS = "";
  public ScanditApiKeyTESTERAndroid = "";

  constructor(
    public platform: Platform,

  ) { }

  // SCANDIT
  scanBarcode() {

    console.log('SCANDIT 1');

    let apiKey = '';
    if (this.platform.is('ios')) {
      apiKey = this.ScanditApiKeyIOS;
    }
    else if (this.platform.is('android')) {
      apiKey = this.ScanditApiKeyAndroid;
    }
    else {
      apiKey = this.ScanditApiKeyTESTERAndroid
    }
    let context = Scandit.DataCaptureContext.forLicenseKey(apiKey);

    //other
    const camera = Scandit.Camera.default;
    context.setFrameSource(camera);

    const settings = new Scandit.BarcodeCaptureSettings();

    // settings.enableSymbologies([
    //   Scandit.Symbology.EAN13UPCA,
    //   Scandit.Symbology.EAN8,
    //   Scandit.Symbology.UPCE,
    //   Scandit.Symbology.QR,
    //   Scandit.Symbology.DataMatrix,
    //   Scandit.Symbology.Code39,
    //   Scandit.Symbology.Code128,
    //   Scandit.Symbology.InterleavedTwoOfFive,
    // ]);

    let picker = Scandit.BarcodeCapture.forContext(context, settings);

    // picker.addListener({
    //   didScan: (barcodeCapture, session) => {
    //     const barcode = session.newlyRecognizedBarcodes[0];
    //     const symbology = new Scandit.SymbologyDescription(barcode.symbology);

    //     // The `alert` call blocks execution until it's dismissed by the user. As no further frames would be processed
    //     // until the alert dialog is dismissed, we're showing the alert through a timeout and disabling the barcode
    //     // capture mode until the dialog is dismissed, as you should not block the BarcodeCaptureListener callbacks for
    //     // longer periods of time. See the documentation to learn more about this.

    //     console.log(`Scanned: ${barcode.data} (${symbology.readableName})`);
    //     barcodeCapture.isEnabled = false;
    //   }
    // });

    const view = Scandit.DataCaptureView.forContext(context);
    view.connectToElement(document.getElementById('data-capture-view'));

    // const overlay = Scandit.BarcodeCaptureOverlay.withBarcodeCaptureForView(picker, view);
    // overlay.viewfinder = new Scandit.RectangularViewfinder();

    console.log("Scandit:" + Scandit);
    console.log("Scandit:" + JSON.stringify(Scandit));
    console.log("picker:" + JSON.stringify(picker))
    console.log("camera:" + JSON.stringify(camera));
    console.log("settings:" + JSON.stringify(settings))
    console.log("view:" + JSON.stringify(view))

    camera.switchToDesiredState(Scandit.FrameSourceState.On);
    picker.isEnabled = true;

    // console.log('SCANDIT 2');

    // const settings = new Scandit.BarcodeCaptureSettings();
    // settings.enableSymbologies([
    //   //1d
    //   Scandit.Symbology.EAN13UPCA,
    //   Scandit.Symbology.EAN8,
    //   Scandit.Symbology.UPCE,
    //   Scandit.Symbology.Code11,
    //   Scandit.Symbology.Code128,
    //   Scandit.Symbology.Code39,
    //   Scandit.Symbology.Code93,
    //   Scandit.Symbology.Code32,
    //   Scandit.Symbology.Code25,
    //   Scandit.Symbology.InterleavedTwoOfFive,
    //   Scandit.Symbology.MSIPlessey,
    //   Scandit.Symbology.GS1Databar,
    //   Scandit.Symbology.GS1DatabarLimited,
    //   Scandit.Symbology.GS1DatabarExpanded,
    //   Scandit.Symbology.Codabar,
    // ]);
    // //2d
    // // Scandit.Symbology.DataMatrix,
    // // Scandit.Symbology.PDF417,
    // // Scandit.Symbology.MicroPDF417,
    // // Scandit.Symbology.Aztec,
    // // Scandit.Symbology.DotCode,
    // // Scandit.Symbology.MaxiCode,
    // // Scandit.Symbology.KIX,
    // // Scandit.Symbology.RM4SCC,
    // // Scandit.Symbology.QR,
    // // Scandit.Symbology.MicroQR
    // const cameraSettings = Scandit.BarcodeCapture.recommendedCameraSettings;

    // cameraSettings.focusRange = Scandit.FocusRange.Full;

    // const cameraFront = Scandit.Camera.atPosition(Scandit.CameraPosition.UserFacing);
    // const cameraBack = Scandit.Camera.atPosition(Scandit.CameraPosition.WorldFacing);

    // if (cameraFront) {
    //   cameraFront.applySettings(cameraSettings);
    // }
    // if (cameraBack) {
    //   cameraBack.desiredTorchState = Scandit.TorchState.On;
    //   cameraBack.applySettings(cameraSettings);
    // }

    // // Depending on the use case further camera settings adjustments can be made here.
    // if (this.core.getSetting(this.core.SettingsCamera) === 'Front') {
    //   this.context.setFrameSource(cameraFront);
    //   cameraFront.switchToDesiredState(Scandit.FrameSourceState.On);
    //   cameraBack.switchToDesiredState(Scandit.FrameSourceState.Off);
    // }
    // else {
    //   this.context.setFrameSource(cameraBack);
    //   cameraFront.switchToDesiredState(Scandit.FrameSourceState.Off);
    //   cameraBack.switchToDesiredState(Scandit.FrameSourceState.On);
    // }

    // // Instantiate the barcode picker by using the settings defined above.
    // console.log('SCANDIT 3');
    // this.picker = Scandit.BarcodeCapture.forContext(this.context, settings);
    // // this.picker.applyScanSettings(settings);
    // console.log('SCANDIT 4');

    // const listener = {
    //   didScan: (barcodeCapture, session) => {
    //     console.log('didScan session: ' + JSON.stringify(session))

    //     this.picker.isEnabled = false;

    //     cameraFront.switchToDesiredState(Scandit.FrameSourceState.Off);
    //     cameraBack.switchToDesiredState(Scandit.FrameSourceState.Off);

    //     this.barcodes = session.newlyRecognizedBarcodes;
    //     console.log('barcodes: ' + JSON.stringify(this.barcodes))
    //     if (this.barcodes.length > 0) {
    //       this.dealWithBarcode(this.barcodes[0].data);
    //     }
    //     else {
    //       this.core.lastBarcodeScannedQuickView = '';
    //     }          // Do something with the barcodes


    //   },
    //   didUpdateSession: (barcodeCapture, session) => {
    //     console.log('didUpdateSession session: ' + JSON.stringify(session));
    //   }
    //   // didCancel: session => {
    //   //   this.core.lastBarcodeScannedQuickView = '';
    //   // },
    //   // didManualSearch: session => {
    //   //   console.log('didManualSearch session: ' + JSON.stringify(session))
    //   //   if (session) {
    //   //     this.dealWithBarcode(session);
    //   //   }
    //   //   else {
    //   //     this.core.lastBarcodeScannedQuickView = '';
    //   //   }
    //   // },
    // };

    // this.picker.addListener(listener);
    // // this.picker.getOverlayView().setTorchEnabled(true);
    // console.log('SCANDIT 5');
    // const view = Scandit.DataCaptureView.forContext(this.context);

    // // Connect the data capture view to the HTML element, so it can fill up its size and follow its position.
    // view.connectToElement(document.getElementById('data-capture-view'));

    // // Add a barcode capture overlay to the data capture view to render the location of captured barcodes on top of
    // // the video preview. This is optional, but recommended for better visual feedback.
    // const overlay = Scandit.BarcodeCaptureOverlay.withBarcodeCaptureForView(this.picker, view);
    // overlay.viewfinder = new Scandit.RectangularViewfinder();

    // // this.picker.getOverlayView().showSearchBar(true);
    // // // this.picker.getOverlayView().setBeepEnabled(true);
    // // console.log('SCANDIT 6');
    // // this.picker.getOverlayView().setCameraSwitchVisibility(Scandit.ScanOverlay.CameraSwitchVisibility.ALWAYS);
    // // this.picker.getOverlayView().setSearchBarPlaceholderText('Scan barcode or type it here');
    // // this.picker.getOverlayView().setSearchBarActionButtonCaption('Submit');
    // // this.picker.getOverlayView().setSearchBarCancelButtonCaption('Cancel');
    // // console.log('SCANDIT 7');

    // // picker.getOverlayView().updateOverlayIfExists();

    // if (this.core.getSetting(this.core.SettingsCamera) === 'Front') {
    //   cameraFront.switchToDesiredState(Scandit.FrameSourceState.On);
    //   cameraBack.switchToDesiredState(Scandit.FrameSourceState.Off);
    // }
    // else {
    //   cameraFront.switchToDesiredState(Scandit.FrameSourceState.Off);
    //   cameraBack.switchToDesiredState(Scandit.FrameSourceState.On);
    // }
    // this.picker.isEnabled = true;
  }

}
