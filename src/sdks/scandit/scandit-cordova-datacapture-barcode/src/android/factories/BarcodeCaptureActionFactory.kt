/*
 * This file is part of the Scandit Data Capture SDK
 *
 * Copyright (C) 2019- Scandit AG. All rights reserved.
 */

package com.scandit.datacapture.cordova.barcode.factories

import com.scandit.datacapture.cordova.barcode.BarcodeActionsListeners
import com.scandit.datacapture.cordova.barcode.actions.ActionClearTrackedBarcodeBrushes
import com.scandit.datacapture.cordova.barcode.actions.ActionClearTrackedBarcodeViews
import com.scandit.datacapture.cordova.barcode.actions.ActionFinishCallback
import com.scandit.datacapture.cordova.barcode.actions.ActionInjectDefaults
import com.scandit.datacapture.cordova.barcode.actions.ActionSetAnchorForTrackedBarcode
import com.scandit.datacapture.cordova.barcode.actions.ActionSetBrushForTrackedBarcode
import com.scandit.datacapture.cordova.barcode.actions.ActionSetOffsetForTrackedBarcode
import com.scandit.datacapture.cordova.barcode.actions.ActionSetViewForTrackedBarcode
import com.scandit.datacapture.cordova.barcode.actions.ActionSubscribeAdvancedOverlay
import com.scandit.datacapture.cordova.barcode.actions.ActionSubscribeBarcodeCapture
import com.scandit.datacapture.cordova.barcode.actions.ActionSubscribeBarcodeTracking
import com.scandit.datacapture.cordova.barcode.actions.ActionSubscribeBasicOverlay
import com.scandit.datacapture.cordova.barcode.utils.FinishCallbackHelper
import com.scandit.datacapture.cordova.core.actions.Action
import com.scandit.datacapture.cordova.core.actions.ActionSend
import com.scandit.datacapture.cordova.core.errors.InvalidActionNameError
import com.scandit.datacapture.cordova.core.factories.ActionFactory

class BarcodeCaptureActionFactory(
    private val listener: BarcodeActionsListeners
) : ActionFactory {

    @Throws(InvalidActionNameError::class)
    override fun provideAction(actionName: String): Action {
        return when (actionName) {
            INJECT_DEFAULTS -> createActionInjectDefaults()
            SUBSCRIBE_BARCODE_CAPTURE -> createActionSubscribeBarcodeCapture()
            SUBSCRIBE_BARCODE_TRACKING -> createActionSubscribeBarcodeTracking()
            SEND_SESSION_UPDATED_EVENT -> createActionSessionUpdated()
            SEND_BARCODE_SCANNED_EVENT -> createActionBarcodeScanned()
            SEND_BRUSH_FOR_TRACKED_BARCODE -> createActionSendBrushForTrackedBarcode()
            SEND_DID_TAP_TRACKED_BARCODE -> createActionTapOnTrackedBarcode()
            FINISH_BLOCKING_ACTION -> createActionFinishBlocking()
            SEND_TRACKING_SESSION_UPDATED_EVENT -> createActionTrackingSessionUpdated()
            SUBSCRIBE_BASIC_OVERLAY_LISTENER -> createActionSubscribeBasicOverlay()
            SUBSCRIBE_ADVANCED_OVERLAY_LISTENER -> createActionSubscribeAdvancedOverlay()
            SET_BRUSH_FOR_TRACKED_BARCODE -> createActionSetBrushForTrackedBarcode()
            CLEAR_TRACKED_BARCODE_BRUSHES -> createActionClearTrackedBarcodeBrushes()
            SEND_VIEW_FOR_TRACKED_BARCODE -> createActionSendViewForTrackedBarcode()
            SEND_OFFSET_FOR_TRACKED_BARCODE -> createActionSendOffsetForTrackedBarcode()
            SEND_ANCHOR_FOR_TRACKED_BARCODE -> createActionSendAnchorForTrackedBarcode()
            SEND_TAP_VIEW_FOR_TRACKED_BARCODE -> createActionSendTapViewForTrackedBarcode()
            SET_VIEW_FOR_TRACKED_BARCODE -> createActionSetViewForTrackedBarcode()
            SET_OFFSET_FOR_TRACKED_BARCODE -> createActionSetOffsetForTrackedBarcode()
            SET_ANCHOR_FOR_TRACKED_BARCODE -> createActionSetAnchorForTrackedBarcode()
            CLEAR_TRACKED_BARCODE_VIEWS -> createActionClearTrackedBarcodeViews()
            else -> throw InvalidActionNameError(actionName)
        }
    }

    override fun canBeRunWithoutCameraPermission(actionName: String): Boolean = true

    private fun createActionInjectDefaults(): Action = ActionInjectDefaults(listener)

    private fun createActionSubscribeBarcodeCapture(): Action = ActionSubscribeBarcodeCapture(
        listener
    )

    private fun createActionSubscribeBarcodeTracking(): Action = ActionSubscribeBarcodeTracking(
        listener
    )

    private fun createActionSessionUpdated(): Action = ActionSend(
        ACTION_CAPTURE_SESSION_UPDATED,
        listener,
        finishCallbackId = ACTION_CAPTURE_SESSION_UPDATED,
        shouldNotifyWhenFinished = true
    )

    private fun createActionBarcodeScanned(): Action = ActionSend(
        ACTION_BARCODE_SCANNED,
        listener,
        finishCallbackId = ACTION_BARCODE_SCANNED,
        shouldNotifyWhenFinished = true
    )

    private fun createActionFinishBlocking(): Action = ActionFinishCallback(
        listener, FinishCallbackHelper()
    )

    private fun createActionTrackingSessionUpdated(): Action = ActionSend(
        ACTION_TRACKING_SESSION_UPDATED,
        listener,
        finishCallbackId = ACTION_TRACKING_SESSION_UPDATED,
        shouldNotifyWhenFinished = true
    )

    private fun createActionSubscribeBasicOverlay(): Action = ActionSubscribeBasicOverlay(
        listener
    )

    private fun createActionSubscribeAdvancedOverlay(): Action = ActionSubscribeAdvancedOverlay(
        listener
    )

    private fun createActionSetBrushForTrackedBarcode(): Action = ActionSetBrushForTrackedBarcode(
        listener
    )

    private fun createActionClearTrackedBarcodeBrushes(): Action = ActionClearTrackedBarcodeBrushes(
        listener
    )

    private fun createActionSendBrushForTrackedBarcode(): Action = ActionSend(
        ACTION_BRUSH_FOR_TRACKED_BARCODE,
        listener,
        finishCallbackId = ACTION_BRUSH_FOR_TRACKED_BARCODE,
        shouldNotifyWhenFinished = true
    )

    private fun createActionTapOnTrackedBarcode(): Action = ActionSend(
        ACTION_TAP_TRACKED_BARCODE, listener
    )

    private fun createActionSendViewForTrackedBarcode(): Action = ActionSend(
        ACTION_VIEW_FOR_TRACKED_BARCODE,
        listener,
        finishCallbackId = ACTION_VIEW_FOR_TRACKED_BARCODE,
        shouldNotifyWhenFinished = true
    )

    private fun createActionSendOffsetForTrackedBarcode(): Action = ActionSend(
        ACTION_OFFSET_FOR_TRACKED_BARCODE,
        listener,
        finishCallbackId = ACTION_OFFSET_FOR_TRACKED_BARCODE,
        shouldNotifyWhenFinished = true
    )

    private fun createActionSendAnchorForTrackedBarcode(): Action = ActionSend(
        ACTION_ANCHOR_FOR_TRACKED_BARCODE,
        listener,
        finishCallbackId = ACTION_ANCHOR_FOR_TRACKED_BARCODE,
        shouldNotifyWhenFinished = true
    )

    private fun createActionSendTapViewForTrackedBarcode(): Action = ActionSend(
        ACTION_TAP_VIEW_FOR_TRACKED_BARCODE,
        listener,
        finishCallbackId = ACTION_TAP_VIEW_FOR_TRACKED_BARCODE,
        shouldNotifyWhenFinished = true
    )

    private fun createActionSetViewForTrackedBarcode(): Action = ActionSetViewForTrackedBarcode(
        listener
    )

    private fun createActionSetOffsetForTrackedBarcode(): Action = ActionSetOffsetForTrackedBarcode(
        listener
    )

    private fun createActionSetAnchorForTrackedBarcode(): Action = ActionSetAnchorForTrackedBarcode(
        listener
    )

    private fun createActionClearTrackedBarcodeViews(): Action = ActionClearTrackedBarcodeViews(
        listener
    )

    companion object {
        private const val INJECT_DEFAULTS = "getDefaults"
        private const val SUBSCRIBE_BARCODE_CAPTURE = "subscribeBarcodeCaptureListener"
        private const val SUBSCRIBE_BARCODE_TRACKING = "subscribeBarcodeTrackingListener"
        private const val FINISH_BLOCKING_ACTION = "finishCallback"
        private const val SUBSCRIBE_BASIC_OVERLAY_LISTENER =
            "subscribeBarcodeTrackingBasicOverlayListener"
        private const val SET_BRUSH_FOR_TRACKED_BARCODE = "setBrushForTrackedBarcode"
        private const val CLEAR_TRACKED_BARCODE_BRUSHES = "clearTrackedBarcodeBrushes"

        private const val SUBSCRIBE_ADVANCED_OVERLAY_LISTENER =
            "subscribeBarcodeTrackingAdvancedOverlayListener"
        const val SEND_VIEW_FOR_TRACKED_BARCODE = "sendViewForTrackedBarcodeEvent"
        const val SEND_OFFSET_FOR_TRACKED_BARCODE = "sendOffsetForTrackedBarcodeEvent"
        const val SEND_ANCHOR_FOR_TRACKED_BARCODE = "sendAnchorForTrackedBarcodeEvent"
        const val SEND_TAP_VIEW_FOR_TRACKED_BARCODE = "sendTapViewForTrackedBarcodeEvent"
        const val SET_VIEW_FOR_TRACKED_BARCODE = "setViewForTrackedBarcode"
        const val SET_OFFSET_FOR_TRACKED_BARCODE = "setOffsetForTrackedBarcode"
        const val SET_ANCHOR_FOR_TRACKED_BARCODE = "setAnchorForTrackedBarcode"
        const val CLEAR_TRACKED_BARCODE_VIEWS = "clearTrackedBarcodeViews"

        const val SEND_BRUSH_FOR_TRACKED_BARCODE = "sendBrushForTrackedBarcodeEvent"
        const val SEND_DID_TAP_TRACKED_BARCODE = "sendDidTapTrackedBarcodeEvent"

        const val SEND_SESSION_UPDATED_EVENT = "sendSessionUpdateEvent"
        const val SEND_BARCODE_SCANNED_EVENT = "sendBarcodeScannedEvent"

        const val ACTION_BARCODE_SCANNED = "didScanInBarcodeCapture"
        const val ACTION_CAPTURE_SESSION_UPDATED = "didUpdateSessionInBarcodeCapture"
        const val ACTION_TRACKING_SESSION_UPDATED = "didUpdateSessionInBarcodeTracking"
        const val ACTION_BRUSH_FOR_TRACKED_BARCODE = "brushForTrackedBarcode"
        const val ACTION_TAP_TRACKED_BARCODE = "didTapTrackedBarcode"
        const val ACTION_VIEW_FOR_TRACKED_BARCODE = "viewForTrackedBarcode"
        const val ACTION_OFFSET_FOR_TRACKED_BARCODE = "offsetForTrackedBarcode"
        const val ACTION_ANCHOR_FOR_TRACKED_BARCODE = "anchorForTrackedBarcode"
        const val ACTION_TAP_VIEW_FOR_TRACKED_BARCODE = "didTapViewForTrackedBarcode"

        const val SEND_TRACKING_SESSION_UPDATED_EVENT = "sendTrackingSessionUpdateEvent"
    }
}
