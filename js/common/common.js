/**
 * @fileOverview
 * Portal共通JavaScript。
 *
 * @author TAKT 平野
 * @since 2011/05/24
 * @version 1.0
 */

/**
 * 共通JavaScriptオブジェクト。
 *
 * @class
 */
function tkLib() {
}

/**
 * コンテキストルート。
 *
 * @final
 * @type String
 */
var CONTEXT_ROOT = "sac";

tkLib.prototype = {

	// ----------------------------------------------------------
	// Constants宣言
	// ----------------------------------------------------------
	/**
	 * エラークラス。
	 *
	 * @final
	 * @type String
	 */
	ERROR_CLASS : "_error",

	/**
	 * アクションURLの拡張子。
	 *
	 * @final
	 * @type String
	 */
	ACTION_EXTENSION : ".action",

	// ----------------------------------------------------------
	// private variables宣言(各画面から直接アクセスしないこと)
	// ----------------------------------------------------------
	/**
	 * インフォメーションメッセージ配列。
	 *
	 * @private
	 * @type Array
	 */
	_info : new Array(),

	/**
	 * エラーメッセージ配列。
	 *
	 * @private
	 * @type Array
	 */
	_error : new Array(),

	/**
	 * サブミットフラグ(ダブルサブミット対応)。
	 *
	 * @private
	 * @type boolean
	 */
	_submitFlg : false,

	/**
	 * 変更フラグ。
	 *
	 * @private
	 * @type boolean
	 */
	_change_flg : false,

	/**
	 * ポップアップウィンドウオブジェクト。
	 *
	 * @private
	 * @type window
	 */
	_pop : null,

	// ----------------------------------------------------------
	// public method宣言
	// ----------------------------------------------------------
	/**
	 * ajaxによる通信を行います。
	 *
	 * @param {String}
	 *            action 通信先のAction名
	 * @param {String}
	 *            params リクエストパラメータ(GET文字列)
	 * @param {Function}
	 *            callback 通信成功時コールバック関数
	 * @param {Object}
	 *            props Ajax通信時のパラメータ
	 */
	ajax : function(action, params, callback, props) {
		var ajaxParam = {
			//url : action + $T.ACTION_EXTENSION,
			url : action,
			data : params,
			dataType : "json",
			cache : false,
			type : "POST",
			async : false,
			success : callback,
			/** @ignore */
			error : function(req, textStatus, errorThrown) {
				alert("Server connection failed.");
			}
		};

		// パラメータの上書きを行う
		for ( var prop in props) {
			ajaxParam.prop = props[prop];
		}
		// ajax通信を行う
		$.ajax(ajaxParam);
	},

	/**
	 * エラーメッセージを追加します。
	 * <p>
	 * エラーメッセージはdisplayError関数を使用して表示します。
	 * </p>
	 *
	 * @param {jQuery
	 *            Object} obj JQueryオブジェクト<br />
	 *            <span style="margin-left:30px">
	 *            nullを渡すとエラー項目へのカーソル、背景色設定が行われませんが、メッセージは追加されます。 </span>
	 * @param {String}
	 *            message メッセージ
	 * @param {String}
	 *            str メッセージ置換文字<br />
	 *            <span style="margin-left:30px">
	 *            置換文字は可変引数です。複数の置換文字がある場合は第3引数、第4引数・・・に指定して下さい。 </span>
	 * @see #displayError
	 */
	addError : function(obj, message, str) {
		if ($.isArray(obj)) {
			for ( var i = 0; i < obj.length; i++) {
				if (i == 0) {
					this.addError(obj[i], message, arguments[2], arguments[3],
							arguments[4], arguments[5]);
				} else {
					this.addError(obj[i], null);
				}
			}
			return;
		}
		if (obj != null) {
			if (!obj.jquery) {
				obj = $("*[name='" + obj + "']");
			}
			// 1つ目のエラー項目にフォーカスを設定する
			if (this._error.length == 0) {
				if (obj.hasClass("date")) {
					// 日付の場合
					obj.children(":first").focus();
				} else if (obj.attr('type') == 'radio') {
					obj[0].focus();
				} else {
					obj.focus();
				}
			}
			// エラー背景色を設定する
			if (obj.hasClass("date")) {
				// 日付の場合
				obj.children(":input").addClass(this.ERROR_CLASS);

			} else if (!obj.hasClass(this.ERROR_CLASS)) {
				obj.addClass(this.ERROR_CLASS);
			}
		}
		if (message) {
			// メッセージ置換用の引数を作成する
			var arg = new Array();
			for ( var i = 1; i < arguments.length; i++) {
				arg.push(arguments[i]);
			}
			// メッセージ置換を行い、メッセージを追加する
			this._error.push(this._buildMessage(arg));
		}
	},

	/**
	 * エラーメッセージが空の場合のみメッセージを追加します。
	 *
	 * @param {String}
	 *            message メッセージ
	 * @param {String}
	 *            str メッセージ置換文字<br />
	 *            <span style="margin-left:30px">
	 *            置換文字は可変引数です。複数の置換文字がある場合は第3引数、第4引数・・・に指定して下さい。 </span>
	 */
	addErrorIfEmpty : function(errObj, message, str) {
		if (this._error.length == 0) {
			this.addError(errObj, message, str);
		}
	},

	/**
	 * エラーメッセージを表示します。
	 *
	 * @param {boolean}
	 *            初期表示メッセージかどうか
	 * @return エラーがある場合はtrue
	 * @type boolean
	 */
	displayError : function(isInit) {
		if (this._error.length > 0) {
			// メッセージが設定されている場合、メッセージをalert表示する
			if (!isInit || !$("#messageControl_message").val()) {
				alert(this._error.join("\n"));
			}
			// 初期化する
			this._error = new Array();
			if (isInit) {
				$("#messageControl_message").val("true");
			}
			return true;
		}
		return false;
	},

	initError : function() {
		this._error = new Array();
		$("." + this.ERROR_CLASS).removeClass(this.ERROR_CLASS);
	},

	/**
	 * インフォメーションメッセージを追加します。
	 * <p>
	 * インフォメーションメッセージはdisplayInfoを使用して表示します。
	 * </p>
	 *
	 * @param {String}
	 *            message メッセージ
	 * @param {String}
	 *            str メッセージ置換文字<br />
	 *            <span style="margin-left:30px">
	 *            置換文字は可変引数です。複数の置換文字がある場合は第2引数、第3引数・・・に指定して下さい。 </span>
	 * @see #displayInfo
	 */
	addInfo : function(message, str) {
		// メッセージ置換を行い、メッセージを追加する
		this._info.push(this._buildMessage(arguments));
	},

	/**
	 * インフォメーションメッセージを表示します。
	 */
	displayInfo : function() {
		if (this._info.length > 0) {
			// メッセージが設定されている場合、メッセージを表示する
			alert(this._info.join("\n"));
			// 初期化する
			this._info = new Array();
		}
	},

	/**
	 * メッセージ置換を行い、確認メッセージを表示します。
	 *
	 * @param {String}
	 *            message メッセージ
	 * @param {String}
	 *            str メッセージ置換文字<br />
	 *            <span style="margin-left:30px">
	 *            置換文字は可変引数です。複数の置換文字がある場合は第2引数、第3引数・・・に指定して下さい。 </span>
	 * @return OKが選択された場合はtrue
	 * @type boolean
	 */
	confirm : function(message, str) {
		return confirm(this
				._buildMessage(Array.prototype.slice.call(arguments)));
	},

	/**
	 * 2度押しの判定後、データを送信します。
	 *
	 * @param {form}
	 *            form submitを行うformオブジェクト
	 * @param {String}
	 *            action 遷移先
	 * @param {function}
	 *            func submit前に実行する関数(チェック関数等)<br />
	 *            <span style="margin-left:30px">
	 *            関数はbooleanを返して下さい。falseが返された場合は処理を中断します。<br />
	 *            処理を行う必要がない場合はnullを指定します。 </span>
	 * @param {boolean}
	 *            enableSubmitFlg falseの場合はsubmitフラグを実行中にしない(既定値：true)
	 */
	submit : function(form, action, func, enableSubmitFlg) {
		if (this._submitFlg) {
			// 実行中の場合は何もしない
			return;
		}
		// 実行中にする
		enableSubmitFlg = StringUtils.isEmpty(enableSubmitFlg) ? true
				: enableSubmitFlg;
		this._submitFlg = enableSubmitFlg;
		if (new String(typeof (func)).toLowerCase() == "function") {
			if (!func(form)) {
				// function戻り値がfalseの場合は処理を中断する
				this._submitFlg = false;
				return;
			}
		}
//		this._submit(form, action + this.ACTION_EXTENSION);
		this._submit(form, action);
	},

	/**
	 * メニュー押下時の処理を行います。
	 *
	 * @param {element}
	 *            button ボタン
	 * @param {String}
	 *            action 遷移先
	 */
	submitMenu : function(button, action) {
		if (!this.confirmChanged()) {
			return false;
		}
		var f = button.form;
		f.elements["pageInfo.menuId"].value = button.id;
		f.elements["pageInfo.history"].value = "";
		this.submit(f, action);
	},

	/**
	 * select候補を再構築します。
	 *
	 * @param {element}
	 *            e エレメント
	 * @param {list}
	 *            list 候補リスト
	 * @param {String}
	 *            valueKey 値の属性名
	 * @param {String}
	 *            labelKey ラベルの属性名
	 * @param {String}
	 *            emptyFlg 空行を追加するかどうか(true:先頭に空白行追加)
	 */
	createSelection : function(e, list, valueKey, labelKey, emptyFlg) {
		var backupValue = e.value;
		var kValue = StringUtils.isEmpty(valueKey) ? "value" : valueKey;
		var kLabel = StringUtils.isEmpty(labelKey) ? "label" : labelKey;
		e.length = 0;

		if (emptyFlg == "true") {
			e.length++;
			e.options[e.length - 1].value = "";
			e.options[e.length - 1].text = "";
		}

		for ( var i = 0; i < list.length; i++) {
			e.length++;
			e.options[e.length - 1].value = (list[i][kValue] != null ? list[i][kValue]
					: "");
			e.options[e.length - 1].text = (list[i][kLabel] != null ? list[i][kLabel]
					: "");
		}
		e.value = backupValue;
	},

	/**
	 * ポップアップ画面を起動します。
	 *
	 * @param {form}
	 *            form submitを行うformオブジェクト
	 * @param {String}
	 *            action 遷移先
	 * @param {String}
	 *            width ウィンドウ幅
	 */
	openWindow : function(form, action, width) {
		if (width == null) {
			width = "850";
		}
		intLeft = (screen.width - width) / 2;
		intTop = (screen.height - 840) / 2;
		this._pop = window.open("about:blank", "pop",
				"scrollbars=yes, resizable=yes, menubar=no, toolbar=no, left="
						+ intLeft + ", top=" + intTop + ", height=840, width="
						+ width);
		var t = form.target;
		form.target = "pop";
		this.submit(form, action, null, false);
		form.target = t;
		this._pop.focus();
	},

	/**
	 * ポップアップ画面を起動します。
	 *
	 * @param {url} 遷移先URL
	 * @param {String}
	 *            action 遷移先
	 * @param {String}
	 *            width ウィンドウ幅
	 */
	openNomalWindow : function(url, action, width) {
		if (width == null) {
			width = "850";
		}
		intLeft = (screen.width - width) / 2;
		intTop = (screen.height - 840) / 2;
		window.open(url,"pop",
				"scrollbars=yes, resizable=yes, menubar=no, toolbar=no, left="
				+ intLeft + ", top=" + intTop + ", height=840, width="
				+ width);
	},

	/**
	 * 値が変更されているか返します。
	 *
	 * @return 値が変更されている場合はtrue
	 * @type boolean
	 */
	isChanged : function() {
		return this._change_flg;
	},

	/**
	 * 値が変更されている場合、確認メッセージを表示します。
	 *
	 * @return 値が変更されていない、またはOKの場合はtrue
	 * @type boolean
	 */
	confirmChanged : function() {
		if (this.isChanged()) {
			if (!this.confirm(IM0006)) {
				return false;
			}
		}
		return true;
	},

	/**
	 * 変更フラグを設定します。
	 *
	 * @param {boolean}
	 *            changed 変更フラグ
	 */
	setChanged : function(changed) {
		if (changed) {
			if (!this._change_flg) {
				if (window.location.hostname == "localhost") {
					$("body").css("background-color", "bisque");
				}
				this._change_flg = true;
			}
		} else {
			$("body").css("background-color", "");
		}
	},

	/**
	 * エレメントの単項目チェックを行います。
	 *
	 * (使用例)<br />
	 * $T.check(<i>$(document.form.element)</i>, {required : true, type :
	 * "n"}, "社員番号")<br />
	 *
	 * @param {JQuery
	 *            Object} obj 対象のJQueryオブジェクトを指定します。
	 * @param {JSON&nbsp;object}
	 *            check チェック内容をJSON形式で指定します。
	 *            <dl style="margin-left:100px">
	 *            <dt>required (boolean) 必須入力チェック</dt>
	 *            <dd>true：必須入力, false：任意入力(省略可)</dd>
	 *            <dt>type (String) 属性チェック</dt>
	 *            <dd> "n" ：数字(0-9)<br />
	 *            "an" ：半角英数記号<br />
	 *            "d" ：日付<br />
	 *            "uid"：ユーザID(半角英数記号かつ英字、数字を必ず含む)<br />
	 *            "pwd"：パスワード(半角英数かつ英字、数字を必ず含む)<br />
	 *            "email"：e-mail<br />
	 *            "file"：アップロードファイル<br />
	 *            </dd>
	 *            </dl>
	 *            <dt>minLength (int) 最小バイト数チェック</dt>
	 *
	 * @param {String}
	 *            name エラー時にメッセージ内で表示する項目名を指定します。
	 * @return true：エラーなし, false：エラーあり
	 * @type boolean
	 */
	check : function(obj, check, name) {
		var value = "";
		if (!obj.jquery) {
			obj = $(obj);
		}
		if (obj.hasClass("date")) {
			// 日付divの場合
			obj = obj.children(":input");
			value = this.buildDateStr(obj);
		} else {
			// 値を取得する
			value = obj.val();
		}

		// 背景色を初期化する
		for ( var i = 0; i < obj.length; i++) {
			if (obj.hasClass(this.ERROR_CLASS)) {
				obj.removeClass(this.ERROR_CLASS);
			}
		}

		// ----------------------------------------------------------
		// required : true
		// 必須入力チェックを行う
		// ----------------------------------------------------------
		if (check.required) {
			if (StringUtils.isEmpty(value)) {
				// 値が空の場合、エラーとする
				// UE0003:You must enter a "{0}".
				this.addError(obj, UE0003, name);
				// 単項目チェックを終了する
				return false;
			}
		}
		if (StringUtils.isNotEmpty(value)) {
			// 値が空でない場合のみ以降のチェックを行う
			// ----------------------------------------------------------
			// type
			// 属性チェックを行う
			// ----------------------------------------------------------
			if (StringUtils.isNotEmpty(check.type)) {
				var checkType = check.type;
				switch (checkType.toLowerCase()) {

				case "n": // 数字
					if (!this.isN(value)) {
						// UE0002:{0} is incorrect.
						this.addError(obj, UE0002, name);
						return false;
					}
					break;
				case "an": // 英数記号
					if (!this.isAN(value)) {
						// UE0002:{0} is incorrect.
						this.addError(obj, UE0002, name);
						return false;
					}
					break;
				case "d": // 日付(YYYYMMDD)
					if (!this.isD(value)) {
						// UE0011:The input form of the date is different.Please
						// reinput "{0}".
						this.addError(obj, UE0011, name);
						return false;
					}
					break;
				case "uid": // ユーザID(半角英数記号かつ英字、数字を必ず含む)
					if (!this.isAN(value)) {
						// UE0032:Invalid character in "{0}". Please enter "{0}"
						// without special characters.
						this.addError(obj, UE0032, name);
						return false;
					}
					if (!this.r_AN(value)) {
						// UE0034:You must enter a "{0}" of the alphanumeric
						// character mixture.
						this.addError(obj, UE0034, name);
						return false;
					}
					break;
				case "pwd": // パスワード(半角英数かつ英字、数字を必ず含む)
					if (!value.match(/^[A-Za-z0-9]*$/g)) {
						// UE0032:Invalid character in "{0}". Please enter "{0}"
						// without special characters.
						this.addError(obj, UE0032, name);
						return false;
					}
					if (!this.r_AN(value)) {
						// UE0034:You must enter a "{0}" of the alphanumeric
						// character mixture.
						this.addError(obj, UE0034, name);
						return false;
					}
					break;
				case "email": // e-mail
					if (!this.isEmail(value)) {
						// UE0002:{0} is incorrect.
						this.addError(obj, UE0002, name);
						return false;
					}
					break;
				case "file": // アップロードファイルパス
					if ($.browser.msie
							&& !value.match(/(^[a-zA-Z]{1}:)|(^\\\\).*/)) {
						// IEの場合はアクセスエラーにならないようにチェックする
						// UE0002:{0} is incorrect.
						this.addError(obj, UE0002, name);
						return false;
					}
					break;
				}
			}
			// ----------------------------------------------------------
			// minLength
			// 最小バイト数チェックを行う
			// ----------------------------------------------------------
			if (StringUtils.isNotEmpty(check.minLength)) {
				if (this.getByte(value) < check.minLength) {
					// UE0030:"{0}" is too short. (min. {1} characters)
					this.addError(obj, UE0030, name, check.minLength);
					return false;
				}
			}
		}
		return true;
	},

	/**
	 * 数字チェックを行います。
	 *
	 * @param {String}
	 *            str 値
	 * @return true：OK, false：エラー
	 * @type boolean
	 */
	isN : function(str) {
		return str.match(/^\d*$/g);
	},

	/**
	 * 英数記号チェックを行います。
	 *
	 * @param {String}
	 *            str 値
	 * @return true：OK, false：エラー
	 * @type boolean
	 */
	isAN : function(str) {
		// 文字数＝バイト数の場合はOK
		return str.length == this.getByte(str);
	},

	/**
	 * 英数字の必須チェックを行います。
	 *
	 * @param {String}
	 *            str 値
	 * @return true：OK, false：エラー
	 * @type boolean
	 */
	r_AN : function(str) {
		return str.match(/[A-Za-z]+/g) && str.match(/\d+/g);
	},

	/**
	 * e-mailチェックを行います。
	 *
	 * @param {String}
	 *            str 値
	 * @return true：OK, false：エラー
	 * @type boolean
	 */
	isEmail : function(str) {
		var emails = str.split(",");
		for ( var i = 0; i < emails.length; i++) {
			if (!emails[i]
					.trim()
					.match(
							/(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)/g)) {
				return false;
			}
		}
		return true;
	},

	/**
	 * 日付チェックを行います。
	 *
	 * @param {String}
	 *            str 値(YYYYMMDD)
	 * @return true：OK, false：エラー
	 * @type boolean
	 */
	isD : function(str) {
		if (StringUtils.isEmpty(str)) {
			// 空の場合はチェックしない
			return true;
		}
		if (!str.match(/^\d{8}$/g)) {
			// 数値で6桁、8桁でない場合はエラー
			return false;
		}
		// 日付オブジェクトを生成する
		var y = Number(str.substr(0, str.length - 4));
		var m = Number(str.substr(str.length - 4, 2)) - 1;
		var d = Number(str.substr(str.length - 2));
		y = y
				+ (y < 100 ? parseInt(new Date().getFullYear() / 100, 10) * 100
						: 0);
		var dt = new Date(y, m, d);
		// 年月日を比較する
		return (dt.getFullYear() == y && dt.getMonth() == m && dt.getDate() == d);
	},

	/**
	 * バイト数を返します。
	 *
	 * @param {String}
	 *            str 値
	 * @return バイト数
	 * @type int
	 */
	getByte : function(str) {
		var len = 0;
		for ( var i = 0; i < str.length; i++) {
			len = len + (escape(str.charAt(i)).length < 4 ? 1 : 2);
		}
		return len;
	},

	/**
	 * 日付項目From、Toの大小比較を行います。
	 *
	 * @param {JQuery
	 *            Object} from FromのJQueryオブジェクトを指定します。
	 * @param {JQuery
	 *            Object} to ToのJQueryオブジェクトを指定します。
	 * @return From &lt;= Toの場合はtrue
	 * @type boolean
	 */
	compareDate : function(from, to) {
		var fromStr = this.buildDateStr(from.children());
		var toStr = this.buildDateStr(to.children());
		if (StringUtils.isNotEmpty(fromStr) && StringUtils.isNotEmpty(toStr)) {
			// from、toが空でない場合
			if (this.isD(fromStr) && this.isD(toStr)) {
				// from、toが日付形式の場合のみチェックする
				return fromStr <= toStr;
			}
		}
		return true;
	},

	/**
	 * 日付項目からYYYYMMDD文字列を構築します。
	 *
	 * @param {JQuery
	 *            Object} obj JQueryオブジェクトを指定します。
	 * @return YYYYMMDD文字列
	 * @type String
	 */
	buildDateStr : function(obj) {
		var y = obj.siblings(".y");
		var m = obj.siblings(".m");
		var d = obj.siblings(".d");
		return y.val() + m.val() + d.val();
	},

	 /**
	  * スラッシュ編集表示を行います。<br>
	  * @param {form} 操作を行うformオブジェクトを指定して下さい。
	  * @param {String} propName プロパティ名
	  */
	 addSlash : function(form, propName) {
		var value = form.elements[propName].value;
		var dispDate = value;

		// スラッシュ除去
		var date = this._removeSlash(value);
		var y;
		var m;
		var d;
		// スラッシュ付加処理
		if (date.match(/^\d{8}$/g)) {
			// 日付オブジェクトを生成する
			y = date.substr(0, 4);
			m = date.substr(4, 2);
			d = date.substr(date.length - 2);
			dispDate = y + "/" + m + "/" + d;
			} else {
			var arr = value.split("/");
			if (arr.length == 3) {
				y = arr[0];
				m = (arr[1].length == 1) ? "0" + arr[1] : arr[1];
				d = (arr[2].length == 1) ? "0" + arr[2] : arr[2];
				dispDate = y + "/" + m + "/" + d;
				if (dispDate.length != 10) {
					dispDate = value;
				}
			}
		}
		// 画面に設定
		form.elements[propName].value = dispDate;
	},

    /**
     * スラッシュ除去<br>
     * @param {String} str 値
     * @return {String} カンマ除去後の値
     * @type String
     */
    _removeSlash : function(str) {
        return str.replace(/\//g,"");
    },

	// ----------------------------------------------------------
	// private method宣言
	// ----------------------------------------------------------
	/**
	 * データを送信します。
	 *
	 * @private
	 * @param {form}
	 *            form フォームオブジェクト
	 * @param {String}
	 *            action 遷移先のAction名
	 */
	_submit : function(form, action) {
		form.action = action;
		form.submit();
	},

	/**
	 * メッセージを組み立てます。
	 *
	 * @private
	 * @param {String}
	 *            message メッセージ
	 * @param {String}
	 *            str メッセージ置換文字<br />
	 *            <span style="margin-left:30px">
	 *            置換文字は可変引数です。複数の置換文字がある場合は第2引数、第3引数・・・に指定して下さい。 </span>
	 * @return メッセージ
	 * @type String
	 */
	_buildMessage : function(message, str) {
		var arr = arguments;
		if (arguments[0].length > 0 && arguments.length == 1) {
			// 引数が配列1つの場合
			arr = arguments[0];
		}
		var msg = arr[0];
		// 置換を行う
		for ( var i = 1; i < arr.length; i++) {
			msg = msg.split("{" + (i - 1) + "}").join(arr[i]);
		}
		return msg;
	}
};

window.$T = new tkLib();

/**
 * 文字列の前後の空白を除去します。
 *
 * @return trim文字列
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * StringUtilsオブジェクトのインスタンスを生成します。
 *
 * @constructor
 */
var StringUtils = new function() {
	/**
	 * 値が空かどうか返します。
	 *
	 * @param {String}
	 *            str 値
	 * @return 空の場合はtrue
	 * @type boolean
	 */
	this.isEmpty = function(str) {
		return (str == null || str.length == 0);
	};

	/**
	 * 値が空でないかどうか返します。
	 *
	 * @param {String}
	 *            str 値
	 * @return 空でない場合はtrue
	 * @type boolean
	 */
	this.isNotEmpty = function(str) {
		return !this.isEmpty(str);
	};
};

/**
 * 画面遷移処理(トップメニュー用)。
 *
 * @param {String}
 *            p_page 遷移URL
 */
function comSubmitTopMenu(p_page) {
	// var changed = window.top.smsl_main ? window.top.smsl_main.$T.isChanged()
	// : $T.isChanged();
	var changed = window.top.portal_main ? window.top.portal_main.$T
			.isChanged() : $T.isChanged();
	if (changed) {
		if (!$T.confirm(IM0006)) {
			return false;
		}
	}
	with (document.forms[0]) {
		method = "GET";
		target = "_top";
		action = p_page;
		submit();
	}
};

/**
 *
 * @param objNm
 * @returns
 */
function $e(objNm) {
	return document.getElementById(objNm);
};

/**
 *
 * @param namespace
 * @param subMenuCd
 * @param menuGroupCd
 * @param actionId
 * @param functionId
 * @returns {Boolean}
 */
function comSubmitMenu(menuGroupCd, actionId, functionId) {
	var form = $e('formMenu');
	form.menuGroupCd.value = menuGroupCd;
	form.functionId.value = functionId
	$T.submit(form, '/' + CONTEXT_ROOT + actionId);
	return false;
};

/**
 *
 * @returns {Boolean}
 */
function comLogout() {
	$T.submit($e('formMenu'), '/' + CONTEXT_ROOT + '/c/ac0000!logout');
	return false;
};

/**
*
* @returns {Boolean}
*/
function comSubmit2Top(formName) {
	window.location = "/" + CONTEXT_ROOT + "/a/aa0110";
	return false;
};

/**
*
* @returns {Boolean}
*/
function comSubmit2Login(formName) {
	window.location = "/" + CONTEXT_ROOT + "/c/ac0000";
	return false;
};

$(function() {
	var targets = $(":input:visible[disabled!=true]").not(".menu, :image");

	if (targets.length == 0) {
		targets = $(":input:visible[disabled!=true]").not(".menu");
	}

	// 値変更の監視
	targets.change(function() {
		$T.setChanged(true);
	});

	// 初期カーソル位置の設定
	var errors = $("." + $T.ERROR_CLASS);
	if (errors.length > 0) {
		errors.get(0).focus();
	} else if (targets.length > 0) {
		// IE8で非互換モードのとき、hiddenがtargetsに含まれてしまうため、下記ロジックで対応する
		for (i = 0; i < targets.length; i++) {
			if ($(targets.get(i)).attr("type") != "hidden" && $(targets.get(i)).attr("tabindex") != "-1") {
				targets.get(i).focus();
				break;
			}
		}
	}

	$( ".calendar" ).datepicker({
	});

});

/**
 * 初期処理.
 */
initialLoad = function() {
	initOnLoad();
	addFieldError();
	$T.displayError();
};

/**
 * 画面別初期処理
 */
initOnLoad = function() {
	// 必要に応じてoverrideする
}

/**
 * エラーの作成処理
 */
addFieldError = function() {
	// 必要に応じてoverrideされる
}

$.extend($.fn,{
	FunctionKeys:function(o){

		var d = document;

		//キーダウンでイベントを取得します
		$(this).keydown(function(e){
			var	k	=	e.keyCode;	//	キーコード
			var	a	=	e.altKey;	//	altキー
			var	s	=	e.shiftKey;	//	shiftキー
			var	c	=	e.ctrlKey;	//	ctrlキー
			var	obj	=	e.target;	//	イベントがあったオブジェクトの取得

			if(o.F5 && k == 116){
				o.F5(obj,s,c,a);

				window.event.keyCode = 0;

				//通常は、戻り値にfalseでイベントキャンセルされます。
				return false;
			}

			if(o.R && k == 82){
				if (c) {
					o.R(obj, s, c, a);

					window.event.keyCode = 0;

					return false;
				}
			}

		});
	}
});

//==============================
//キー項入力 イベント
//==============================
$(document).FunctionKeys({
	// F5:function(obj,s,c,a){alert("F5キーは無効です。");}
	// ,R:function(obj,s,c,a){alert("ctrl + Rは無効です。");}
});


